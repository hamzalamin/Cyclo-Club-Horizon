package com.wora.services.impl;

import com.wora.models.dtos.CompetitionDto;
import com.wora.models.entities.Competition;
import com.wora.repositories.CompetitionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceTest {
    @Mock
    private CompetitionRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompetitionService service;


    @Test
    @DisplayName("findById Return Competition If Exist")
    void findById_returnCompetitionIfExist() {
        Long id = 14L;
        String name = "TAWAF al-Maghrib";
        LocalDate startDate = LocalDate.parse("2020-12-12");
        LocalDate endDate = LocalDate.parse("2020-12-12");
        String location = "Maroc";


        Competition competition = new Competition(name, startDate, endDate, location);

        CompetitionDto competitionDto = new CompetitionDto(competition.getName(), competition.getStartDate(), competition.getEndDate(), competition.getLocation(), competition.getGeneralResults(), competition.getRounds());

        when(repository.findById(id)).thenReturn(Optional.of(competition));
        when(modelMapper.map(competition, CompetitionDto.class)).thenReturn(competitionDto);

        CompetitionDto result = service.getById(id);

        assertEquals(competition.getName(), result.name());
        verify(repository).findById(id);
    }

    @Test
    @DisplayName("findById() Should Throw Exception When Id Is Null")
    void findById_ShouldThrowExceptionWhenIdIsNull(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getById(id));
    }

}
