package com.shandilya.movie.locker;

import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.Show;
import java.util.List;

public interface ISeatLocker {
    void lockSeats(Show show, List<Seat> seats, String user);
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLock(Show show, Seat seat, String user);
    List<Seat> getLockedSeats(Show show);
}