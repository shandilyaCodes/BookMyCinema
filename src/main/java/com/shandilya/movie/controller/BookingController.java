package com.shandilya.movie.controller;

import com.shandilya.movie.dto.BookingDTO;
import com.shandilya.movie.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{showId}")
    public ResponseEntity<List<BookingDTO>> getAllBookingByShowId(@PathVariable Long showId) {
        final List<BookingDTO> allBookings = bookingService.getAllBookings(showId);
        return new ResponseEntity<>(allBookings, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingByBookingId(@PathVariable Long bookingId) {
        final BookingDTO bookingDTO = bookingService.findByBookingId(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(bookingService.findByUserId(userId), HttpStatus.OK);
    }
}