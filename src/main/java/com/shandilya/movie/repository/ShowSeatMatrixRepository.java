package com.shandilya.movie.repository;

import com.shandilya.movie.model.ShowSeatMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShowSeatMatrixRepository extends JpaRepository<ShowSeatMatrix, Long> {
    Optional<ShowSeatMatrix> findByShowId(Long showId);
}