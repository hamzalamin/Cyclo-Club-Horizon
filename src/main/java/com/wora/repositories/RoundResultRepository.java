package com.wora.repositories;

import com.wora.models.entities.RoundResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundResultRepository extends JpaRepository<RoundResult, Long> {
}
