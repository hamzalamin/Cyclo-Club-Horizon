package com.wora.repositories;

import com.wora.models.entities.RoundResult;
import com.wora.models.entities.embeddables.RoundResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundResultRepository extends JpaRepository<RoundResult, RoundResultId> {
}
