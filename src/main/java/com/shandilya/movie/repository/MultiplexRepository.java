package com.shandilya.movie.repository;

import com.shandilya.movie.model.Multiplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiplexRepository extends JpaRepository<Multiplex, Long> {

}