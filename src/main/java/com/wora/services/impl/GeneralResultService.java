package com.wora.services.impl;

import com.wora.mappers.GeneralResultMapper;
import com.wora.models.dtos.generalResult.CreateGeneralResultDto;
import com.wora.models.dtos.generalResult.GeneralResultDto;
import com.wora.models.entities.Competition;
import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Rider;
import com.wora.repositories.CompetitionRepository;
import com.wora.repositories.GeneralResultRepository;
import com.wora.repositories.RiderRepository;
import com.wora.services.IGeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralResultService implements IGeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;
    @Autowired
    private GeneralResultMapper generalResultMapper;
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private CompetitionRepository competitionRepository;


    @Override
    public GeneralResultDto create(CreateGeneralResultDto dto) {
        Long competitionId = dto.competitionId();
        Long riderId = dto.riderId();

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("competion with this id is not found"));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider with this Id is not found"));

        GeneralResult generalResult = generalResultMapper.createEntity(dto);
        generalResult.setRider(rider);
        generalResult.setCompetition(competition);
        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);
        return generalResultMapper.toDto(savedGeneralResult);
    }
}
