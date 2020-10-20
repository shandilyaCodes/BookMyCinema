package com.shandilya.movie.repository;

import com.shandilya.movie.model.MultiplexAudiMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MultiplexAudiMappingRepository extends JpaRepository<MultiplexAudiMapping, Long> {

    List<MultiplexAudiMapping> findByMultiplexId(Long id);
}