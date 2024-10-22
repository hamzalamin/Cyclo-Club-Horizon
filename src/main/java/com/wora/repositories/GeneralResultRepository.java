package com.wora.repositories;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.embeddables.GeneralResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {
}
