package com.shandilya.movie.service;

import com.shandilya.movie.dto.ShowDTO;
import com.shandilya.movie.exceptions.AudiNotFoundException;
import com.shandilya.movie.exceptions.AudiNotPresentInMultiplexException;
import com.shandilya.movie.exceptions.MovieNotFoundException;
import com.shandilya.movie.exceptions.MultiplexNotFoundException;
import com.shandilya.movie.model.*;
import com.shandilya.movie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final AudiRepository audiRepository;
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final MultiplexRepository multiplexRepository;
    private final ShowSeatMatrixRepository showSeatMatrixRepository;
    private final MultiplexAudiMappingRepository multiplexAudiMappingRepository;

    public void createShow(ShowDTO showDTO) {
        final Optional<Movie> movie = movieRepository.findById(showDTO.getMovieId());
        final Optional<Audi> audi = audiRepository.findById(showDTO.getAudiId());
        final Optional<Multiplex> multiplex = multiplexRepository.findById(showDTO.getMultiplexId());
        final List<MultiplexAudiMapping> mapping = multiplexAudiMappingRepository.findByMultiplexId(showDTO.getMultiplexId());

        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie Not Found");
        }

        if (!audi.isPresent()) {
            throw new AudiNotFoundException("Audi Not Found");
        }

        if (!multiplex.isPresent()) {
            throw new MultiplexNotFoundException("Multiplex Not Found");
        }

        if (!isAudiPresentInMultiplex(mapping, audi.get())) {
            throw new AudiNotPresentInMultiplexException("Audi Not Present In the Multiplex");
        }

        // TODO : Handle the overlapping time for show creation

        final Show save = showRepository.save(showDTO.transform());
        final Long showId = save.getId();
        initialiseShowSeatMatrix(showId, showDTO.getAudiId());
    }

    private boolean isAudiPresentInMultiplex(List<MultiplexAudiMapping> mapping, Audi audi) {
        boolean isPresent = false;
        for (MultiplexAudiMapping map : mapping) {
            if (map.getAudiId().equals(audi.getId())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    private void initialiseShowSeatMatrix(Long showId, Long audiId) {
        StringBuilder stringBuilder = new StringBuilder();
        final Optional<Audi> audi = audiRepository.findById(audiId);
        if (!audi.isPresent()) {
            throw new AudiNotFoundException("Audi Not Found!");
        }
        final Audi audiDet = audi.get();
        final int totalSeats = audiDet.getSeatRows() * audiDet.getSeatColumns();
        for (int i = 1; i <= totalSeats ; i++) {
            stringBuilder.append(i).append(",");
        }
        String seatsAvailable = stringBuilder.toString();
        final ShowSeatMatrix seatMatrix = ShowSeatMatrix.builder()
                .availableSeats(seatsAvailable)
                .showId(showId)
                .build();
        showSeatMatrixRepository.save(seatMatrix);
    }
}