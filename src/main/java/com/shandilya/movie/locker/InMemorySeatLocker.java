package com.shandilya.movie.locker;

import com.shandilya.movie.exceptions.SeatTemporarilyUnavailableException;
import com.shandilya.movie.model.Seat;
import com.shandilya.movie.model.SeatLock;
import com.shandilya.movie.model.Show;
import java.util.*;

public class InMemorySeatLocker implements ISeatLocker {

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLocker(Integer lockTimeout) {
        this.lockTimeout = lockTimeout;
        this.locks = new HashMap<>();
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                throw new SeatTemporarilyUnavailableException("This Seat is currently not available");
            }
        }
        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) &&
                locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (!locks.containsKey(show)) {
            return Collections.emptyList();
        }
        ArrayList<Seat> lockedSeats = new ArrayList<>();
        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(Show show, Seat seat) {
        return locks.containsKey(show) &&
                locks.get(show).containsKey(seat) &&
                !locks.get(show).get(seat).isLockExpired();
    }

    private void lockSeat(Show show, Seat seat, String user, Integer lockTimeout) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        SeatLock lock = new SeatLock(seat, show, lockTimeout, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private void unlockSeat(Show show, Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }
}