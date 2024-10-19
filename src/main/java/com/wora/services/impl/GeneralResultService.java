package com.wora.services.impl;

import com.wora.models.dtos.requests.GeneralResultDtoReq;
import com.wora.models.dtos.responses.GeneralResultDtoRes;
import com.wora.models.entities.Competition;
import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Rider;
import com.wora.repositories.CompetitionRepository;
import com.wora.repositories.GeneralResultRepository;
import com.wora.repositories.RiderRepository;
import com.wora.services.IGeneralResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralResultService implements IGeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private RiderRepository riderRepository;


    @Override
    public GeneralResultDtoRes create(GeneralResultDtoReq dto) {
        Long competitionId = dto.competitionId();
        Long riderId = dto.riderId();

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("competion with this id is not found"));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider with this Id is not found"));

        GeneralResult generalResult = new GeneralResult(competition, rider);

        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);
        return modelMapper.map(savedGeneralResult, GeneralResultDtoRes.class);
    }
}
