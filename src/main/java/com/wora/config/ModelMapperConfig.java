package com.wora.config;

import com.wora.models.dtos.CompetitionDto;
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
            CompetitionDto source = context.getSource();
            return new Competition(source.name(), source.startDate(), source.endDate(), source.location());
        }, CompetitionDto.class, Competition.class);

        return mapper;
    }



}
