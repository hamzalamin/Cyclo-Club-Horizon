package com.wora.repositories;

import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.embeddables.GeneralResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {

    @Query("DELETE FROM GeneralResult gr WHERE gr.id.riderId = :riderId AND gr.id.competitionId = :competitionId")
    @Modifying
    void deleteByGeneralResultId(@Param("riderId") Long riderId, @Param("competitionId") Long competitionId);
}
