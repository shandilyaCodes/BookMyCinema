package com.shandilya.movie.repository;

import com.shandilya.movie.model.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {

    List<SeatLock> findByShowId(Long showId);
}