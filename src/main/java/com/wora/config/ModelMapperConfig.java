package com.wora.config;

import com.wora.models.dtos.requests.CompetitionDtoReq;
import com.wora.models.entities.Competition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.registerModule(new RecordModule());

        mapper.addConverter(context -> {
            CompetitionDtoReq source = context.getSource();
            return new Competition(source.name(), source.startDate(), source.endDate(), source.location());
        }, CompetitionDtoReq.class, Competition.class);


        Converter<Competition, CompetitionDtoReq> toCompetitionDtoReqConverter =
                context -> new CompetitionDtoReq(
                        context.getSource().getName(),
                        context.getSource().getStartDate(),
                        context.getSource().getEndDate(),
                        context.getSource().getLocation()
                );

        mapper.addConverter(toCompetitionDtoReqConverter);


        return mapper;
    }



}
