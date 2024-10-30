package com.wora.services.impl;

import com.wora.exception.CompetitionNotFoundException;
import com.wora.mappers.CompetitionMapper;
import com.wora.models.dtos.competition.CompetitionDto;
import com.wora.models.dtos.competition.CreateCompetitionDto;
import com.wora.models.dtos.competition.UpdateCompetitionDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
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

    @Test
    @DisplayName("update() Should update competition successfully")
    void update_shouldUpdateSuccessfully() {
        Long id = 14L;
        String name = "Updated TAWAF al-Maghrib";
            LocalDate startDate = LocalDate.parse("2021-01-01");
        LocalDate endDate = LocalDate.parse("2021-01-01");
        String location = "Updated Maroc";
        Boolean isClosed = true;

        UpdateCompetitionDto updateCompetitionDto = new UpdateCompetitionDto(name, startDate, endDate, location, isClosed);

        Competition updatedCompetitionEntity = new Competition();
        updatedCompetitionEntity.setId(id);
        updatedCompetitionEntity.setName(name);
        updatedCompetitionEntity.setStartDate(startDate);
        updatedCompetitionEntity.setEndDate(endDate);
        updatedCompetitionEntity.setLocation(location);
        updatedCompetitionEntity.setIsClosed(isClosed);


        Competition originalCompetition = new Competition();
        originalCompetition.setId(id);
        originalCompetition.setName("Original TAWAF al-Maghrib");
        originalCompetition.setStartDate(LocalDate.parse("2020-12-12"));
        originalCompetition.setEndDate(LocalDate.parse("2020-12-12"));
        originalCompetition.setLocation("Original Maroc");
        originalCompetition.setIsClosed(false);

        CompetitionDto expected = new CompetitionDto(id, name, startDate, endDate, location, isClosed, List.of(), List.of());

        when(repository.findById(id)).thenReturn(Optional.of(originalCompetition));
        when(repository.save(any(Competition.class))).thenReturn(updatedCompetitionEntity);
        when(competitionMapper.toDto(updatedCompetitionEntity)).thenReturn(expected);


        CompetitionDto result = service.update(id, updateCompetitionDto);

        assertNotNull(result);
        assertEquals(result, expected);

        verify(repository).findById(id);
        verify(repository).save(any(Competition.class));
        verify(competitionMapper).toDto(updatedCompetitionEntity);
    }


    @Test
    @DisplayName("update() Should Throw Exception When Competition Not Found")
    void updateShouldThrowExceptionWhenCompetitionNotFound() {
        Long id = 14L;
        UpdateCompetitionDto updateCompetitionDto = new UpdateCompetitionDto("Updated Name", LocalDate.now(), LocalDate.now(), "Updated Location", false);

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.update(id, updateCompetitionDto);
        });

        String expectedMessage = "Competition not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(repository).findById(id);
    }

    @Test
    @DisplayName("findAll() Should Return List of Competitions")
    void findAllShouldReturnListOfCompetitions() {
        Competition competition1 = new Competition();
        competition1.setId(1L);
        competition1.setName("Competition 1");
        competition1.setStartDate(LocalDate.parse("2021-01-01"));
        competition1.setEndDate(LocalDate.parse("2021-12-31"));
        competition1.setLocation("maroc");
        competition1.setIsClosed(false);

        Competition competition2 = new Competition();
        competition2.setId(2L);
        competition2.setName("Competition 2");
        competition2.setStartDate(LocalDate.parse("2021-01-01"));
        competition2.setEndDate(LocalDate.parse("2021-12-31"));
        competition2.setLocation("maroc");
        competition2.setIsClosed(false);
        List<Competition> competitions = Arrays.asList(competition1, competition2);

        when(repository.findAll()).thenReturn(competitions);

        CompetitionDto dto1 = new CompetitionDto(1L, "Competition 1", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-01"), "maroc", false, List.of(), List.of());
        CompetitionDto dto2 = new CompetitionDto(2L, "Competition 2", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-01"), "maroc", false, List.of(), List.of());

        when(competitionMapper.toDto(competition1)).thenReturn(dto1);
        when(competitionMapper.toDto(competition2)).thenReturn(dto2);

        List<CompetitionDto> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));

        verify(repository).findAll();
    }

    @Test
    @DisplayName("getAll() Should throw an exception when repository fails")
    void getAll_shouldThrowException_whenRepositoryFails() {
        when(repository.findAll()).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getAll();
        });

        assertEquals("Database error", exception.getMessage());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("delete() Should Delete Competition Successfully")
    void deleteShouldDeleteCompetitionSuccessfully() {
        Long competitionId = 1L;

        doNothing().when(repository).deleteById(competitionId);
        when(repository.findById(competitionId)).thenReturn(Optional.of(new Competition()));

        service.delete(competitionId);

        verify(repository).findById(competitionId);
        verify(repository).deleteById(competitionId);
    }

    @Test
    @DisplayName("delete() Should Throw Exception When Competition Not Found")
    void deleteShouldThrowExceptionWhenCompetitionNotFound() {
        Long competitionId = 1L;
        when(repository.findById(competitionId)).thenReturn(Optional.empty());

        CompetitionNotFoundException thrown = assertThrows(CompetitionNotFoundException.class, () -> {
            service.delete(competitionId);
        });
        assertEquals("Competition not found with ID: " + competitionId, thrown.getMessage());
        verify(repository, never()).deleteById(competitionId);
    }


}
