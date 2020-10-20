package com.shandilya.movie.service;

import com.shandilya.movie.model.SeatLock;
import com.shandilya.movie.model.Show;
import com.shandilya.movie.model.ShowSeatMatrix;
import com.shandilya.movie.repository.SeatLockRepository;
import com.shandilya.movie.repository.ShowRepository;
import com.shandilya.movie.repository.ShowSeatMatrixRepository;
import com.shandilya.movie.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SeatLockService {

    private final SeatLockRepository seatLockRepository;
    private final ShowSeatMatrixRepository showSeatMatrixRepository;

    public List<String> findLockedSeatsByShowId(Long showId) {
        List<SeatLock> locks;
        List<String> lockedSeats = new ArrayList<>();
        locks = seatLockRepository.findByShowId(showId);
        refreshLocks(locks);
        locks = seatLockRepository.findByShowId(showId);
        for (SeatLock lock : locks) {
            lockedSeats.addAll(Arrays.asList(lock.getBookedSeats().split(",")));
        }
        return lockedSeats;
    }

    private void refreshLocks(List<SeatLock> locks) {
        List<String> seatsMatrix = new ArrayList<>();
        for (SeatLock lock : locks) {
            if (isStale(lock)) {
                // If the Lock timeout has expired
                // Remove the lock from the lock table
                seatLockRepository.deleteById(lock.getId());
                // Return the seats for this particular lock to the pool of available seats in the ShowSeat Matrix
                final Optional<ShowSeatMatrix> showSeatMatrix = showSeatMatrixRepository.findByShowId(lock.getShowId());
                if (showSeatMatrix.isPresent()) {
                    seatsMatrix = CommonUtils.csvToString(showSeatMatrix.get().getAvailableSeats());
                }
                final List<String> releasedSeats = CommonUtils.csvToString(lock.getBookedSeats());
                final List<String> intersection = CommonUtils.intersection(seatsMatrix, releasedSeats);
                if (intersection.isEmpty()) {
                    final String toBePushedSeats = CommonUtils.listToCsv(CommonUtils.union(seatsMatrix, releasedSeats));
                    if (showSeatMatrix.isPresent()) {
                        showSeatMatrix.get().setAvailableSeats(toBePushedSeats);
                        //showSeatMatrixRepository.save(showSeatMatrix);
                    }
                }
            }
        }
    }

    private boolean isStale(SeatLock lock) {
        final Instant lockInstant = lock.getStartTime().toInstant().plusSeconds(lock.getTimeoutSeconds());
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

    public void acquireLock(Long userId, Long showId, String seats) {
        // Capture the Seats to be booked as part of this booking
        // Create the Lock and persist
        final SeatLock lock = SeatLock.builder()
                .userId(userId)
                .showId(showId)
                .startTime(new Date())
                .bookedSeats(seats)
                .timeoutSeconds(3600)
                .build();
        seatLockRepository.save(lock);
    }
}