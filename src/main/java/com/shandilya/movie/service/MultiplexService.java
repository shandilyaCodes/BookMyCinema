package com.shandilya.movie.service;

import com.shandilya.movie.dto.AudiDTO;
import com.shandilya.movie.dto.MultiplexDTO;
import com.shandilya.movie.exceptions.MultiplexNotFoundException;
import com.shandilya.movie.model.Audi;
import com.shandilya.movie.model.Multiplex;
import com.shandilya.movie.model.MultiplexAudiMapping;
import com.shandilya.movie.repository.AudiRepository;
import com.shandilya.movie.repository.MultiplexAudiMappingRepository;
import com.shandilya.movie.repository.MultiplexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MultiplexService {

    private final AudiRepository audiRepository;
    private final MultiplexRepository multiplexRepository;
    private final MultiplexAudiMappingRepository multiplexAudiMappingRepository;

    public void registerMultiplex(MultiplexDTO multiplexDTO) {
        multiplexRepository.save(multiplexDTO.transform());
    }

    public List<MultiplexDTO> findAllMultiplexes() {
        final List<Multiplex> all = multiplexRepository.findAll();
        return all.stream()
                .map(Multiplex::transform)
                .collect(Collectors.toList());
    }

    public void addAudiToMultiplex(Long multiplexId, AudiDTO audiDTO) {
        final Optional<Multiplex> multiplex = multiplexRepository.findById(multiplexId);
        if (!multiplex.isPresent()) {
            throw new MultiplexNotFoundException("No Multiplex Found with this ID");
        }
        final Audi audi = audiRepository.save(audiDTO.transform());
        final MultiplexAudiMapping mapping = MultiplexAudiMapping.builder()
                .audiId(audi.getId())
                .multiplexId(multiplexId)
                .build();
        multiplexAudiMappingRepository.save(mapping);
    }
}