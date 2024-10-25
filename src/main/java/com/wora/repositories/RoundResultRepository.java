package com.wora.repositories;

import com.wora.models.entities.RoundResult;
import com.wora.models.entities.embeddables.RoundResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoundResultRepository extends JpaRepository<RoundResult, RoundResultId> {

    @Query("DELETE FROM RoundResult gr WHERE gr.rider.id = :riderId AND gr.round.id = :roundId")
    @Modifying
    void deleteByRoundResultId(@Param("riderId") Long riderId, @Param("roundId") Long roundId);

}
