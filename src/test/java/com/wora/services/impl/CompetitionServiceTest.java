package com.wora.services.impl;

import com.wora.mappers.CompetitionMapper;
import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.models.dtos.generalResult.EmbeddedGeneralResultDto;
import com.wora.models.dtos.round.EmbeddedRoundDto;
import com.wora.models.entities.Competition;
import com.wora.models.entities.GeneralResult;
import com.wora.models.entities.Round;
import com.wora.repositories.CompetitionRepository;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceTest {
    @Mock
    private CompetitionRepository repository;

    @Mock
    private CompetitionMapper competitionMapper;

    @InjectMocks
    private CompetitionService service;

    @Mock
    private Validator validator;


    @Test
    @DisplayName("findById Return Competition If Exist")
    void findById_returnCompetitionIfExist() {
        Long id = 14L;
        String name = "TAWAF al-Maghrib";
        LocalDate startDate = LocalDate.parse("2020-12-12");
        LocalDate endDate = LocalDate.parse("2020-12-12");
        String location = "Maroc";
        Boolean isClosed = false;
        List<GeneralResult> generalResults = List.of();
        List<Round> rounds = List.of();

        Competition competition = new Competition(id, name, startDate, endDate, location, isClosed, generalResults, rounds);
        CompetitionDto competitionDto = new CompetitionDto(id, name, startDate, endDate, location, isClosed, List.of(), List.of());

        when(repository.findById(id)).thenReturn(Optional.of(competition));
        when(competitionMapper.toDto(competition)).thenReturn(competitionDto);

        CompetitionDto result = service.getById(id);

        assertNotNull(result);
        assertEquals(competitionDto, result);

        verify(repository).findById(id);
        verify(competitionMapper).toDto(competition);

    }

    @Test
    @DisplayName("findById() Should Throw Exception When Id Is Null")
    void findById_ShouldThrowExceptionWhenIdIsNull(){
        Long id = null;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(id));
    }


    @Test
    @DisplayName("createCompetition() Should Throw Exception When CompetitionDto Is Null")
    void createCompetition_ShouldThrowExceptionWhenCompetitionDtoIsNull() {
        CreateCompetitionDto competitionDto = null;
        assertThrows(IllegalArgumentException.class, () -> service.create(competitionDto));
    }

    @Test
    @DisplayName("create() Should create successfully")
    void create_shouldCreateSuccessfully(){
        String name = "TAWAF al-Maghrib";
        LocalDate startDate = LocalDate.parse("2020-12-12");
        LocalDate endDate = LocalDate.parse("2020-12-12");
        String location = "Maroc";
        Boolean isClosed = false;
        CreateCompetitionDto createCompetitionDto = new CreateCompetitionDto(name, startDate, endDate, location, isClosed);

        Competition competitionEntity = new Competition();
        competitionEntity.setName(name);
        competitionEntity.setStartDate(startDate);
        competitionEntity.setEndDate(endDate);
        competitionEntity.setLocation(location);
        competitionEntity.setIsClosed(isClosed);


        Competition savedCompetition = new Competition();
        savedCompetition.setName(name);
        savedCompetition.setStartDate(startDate);
        savedCompetition.setEndDate(endDate);
        savedCompetition.setLocation(location);
        savedCompetition.setIsClosed(isClosed);

        CompetitionDto expectedCompetitionDto = new CompetitionDto(savedCompetition.getId(), name, startDate, endDate, location, isClosed, List.of(), List.of());

        when(competitionMapper.toEntity(createCompetitionDto)).thenReturn(competitionEntity);
        when(repository.save(competitionEntity)).thenReturn(savedCompetition);
        when(competitionMapper.toDto(savedCompetition)).thenReturn(expectedCompetitionDto);

        CompetitionDto result = service.create(createCompetitionDto);

        assertNotNull(result);
        assertEquals(expectedCompetitionDto, result);
        verify(repository).save(competitionEntity);
        verify(competitionMapper).toDto(savedCompetition);




    }

}
