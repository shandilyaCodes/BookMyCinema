package com.shandilya.movie.service;

import com.shandilya.movie.dto.BookingDTO;
import com.shandilya.movie.exceptions.BookingNotFoundException;
import com.shandilya.movie.exceptions.SeatAlreadyBookedException;
import com.shandilya.movie.model.Booking;
import com.shandilya.movie.repository.BookingRepository;
import com.shandilya.movie.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SeatLockService seatLockService;
    private final BookingRepository bookingRepository;

    public void createBooking(BookingDTO bookingDTO) {
        // Check the availability of seats
        // 1. Find the seats locked for this showID
        final List<String> lockedSeatsByShowId = seatLockService.findLockedSeatsByShowId(bookingDTO.getShowId());
        // 2. Find the seats need to be booked
        final List<String> toBeBookedSeats = CommonUtils.csvToString(bookingDTO.getSeats());
        // 3. Find the intersection of two seat lists
        final List<String> commonSeats = CommonUtils.intersection(lockedSeatsByShowId, toBeBookedSeats);
        // 4. If some common elements found, don't allow booking
        if (!commonSeats.isEmpty()) {
            throw new SeatAlreadyBookedException("Some seats are already booked");
        }

        bookingRepository.save(bookingDTO.transform());

        // 1. Acquire Lock on the bookingDTO.seats and push the same to DB
        seatLockService.acquireLock(bookingDTO.getUserId(), bookingDTO.getShowId(), bookingDTO.getSeats());
    }

    public List<BookingDTO> getAllBookings(Long showId) {
        return bookingRepository.findByShowId(showId)
                .stream()
                .map(Booking::transform)
                .collect(Collectors.toList());
    }

    public BookingDTO findByBookingId(Long bookingId) {
        final Optional<Booking> byId = bookingRepository.findById(bookingId);
        if (!byId.isPresent()) {
            throw new BookingNotFoundException("Booking Not Found!");
        }
        return byId.get().transform();
    }

    public List<BookingDTO> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(Booking::transform)
                .collect(Collectors.toList());
    }


}