package com.wora.services.impl;


import com.wora.models.entities.GeneralResult;
import com.wora.repositories.GeneralResultRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class GeneralResultServiceTest {

    @Mock
    private GeneralResultRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GeneralResultService service;

    @Test
    @DisplayName("findById() Return GeneralResult If Exist")
    void findByIdReturnGeneralResultIfExist(){

    }
}

