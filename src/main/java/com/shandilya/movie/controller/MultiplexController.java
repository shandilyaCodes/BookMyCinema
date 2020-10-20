package com.shandilya.movie.controller;

import com.shandilya.movie.dto.AudiDTO;
import com.shandilya.movie.dto.MultiplexDTO;
import com.shandilya.movie.dto.ShowDTO;
import com.shandilya.movie.service.MultiplexService;
import com.shandilya.movie.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/multiplex")
public class MultiplexController {

    private final ShowService showService;
    private final MultiplexService multiplexService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMultiplex(@RequestBody MultiplexDTO multiplexDTO) {
        multiplexService.registerMultiplex(multiplexDTO);
        return ResponseEntity.ok("");
    }

    @GetMapping("/all")
    public ResponseEntity<List<MultiplexDTO>> getAllMultiplexes() {
        final List<MultiplexDTO> allMultiplexDTOS = multiplexService.findAllMultiplexes();
        return new ResponseEntity<>(allMultiplexDTOS, HttpStatus.OK);
    }

    @PostMapping("/{multiplexId}/audi/add")
    public ResponseEntity<?> addScreen(@PathVariable Long multiplexId,
                                       @RequestBody AudiDTO audiDTO) {
        multiplexService.addAudiToMultiplex(multiplexId, audiDTO);
        return ResponseEntity.ok("");
    }

    @PostMapping("/show/add")
    public ResponseEntity<?> createShow(@RequestBody ShowDTO showDTO) {
        showService.createShow(showDTO);
        return ResponseEntity.ok("");
    }
}