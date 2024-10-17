package com.wora.config;

import com.wora.models.dtos.requests.CompetitionDtoReq;
import com.wora.models.entities.Competition;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(context -> {
            CompetitionDtoReq source = context.getSource();
            return new Competition(source.name(), source.startDate(), source.endDate(), source.location());
        }, CompetitionDtoReq.class, Competition.class);

        return mapper;
    }



}
