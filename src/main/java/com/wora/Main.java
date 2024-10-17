package com.wora;

import com.wora.config.AppConfig;
import com.wora.config.ModelMapperConfig;
import com.wora.models.dtos.requests.CompetitionDtoReq;
import com.wora.services.ICompetitionService;
import com.wora.services.impl.CompetitionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, ModelMapperConfig.class);
        ICompetitionService competitionService = context.getBean(CompetitionService.class);
        //todo: create competition
//        LocalDate endDate = LocalDate.of(2024, 10, 1);
//        String location = "San sero";
//        String name = "wa jryyy Competition";
//        LocalDate startDate = LocalDate.of(2024, 10, 1);
//        CompetitionDtoReq competitionDto = new CompetitionDtoReq(endDate, location, name, startDate);
//        CompetitionDtoReq createdCompetition = competitionService.create(competitionDto);
//
//        System.out.println("Competition created: " + createdCompetition);


        //todo: update competition
//        Long competitionId = 902L;
//        LocalDate startDate = LocalDate.of(2025, 12, 1);
//        String location = "AGADIR";
//        String name = "lo2ay";
//        LocalDate endDate = LocalDate.of(2025, 11, 1);
//        CompetitionDtoReq updatedCompetitionDto = new CompetitionDtoReq(startDate, location, name, endDate);
//        CompetitionDtoReq updatedCompetition = competitionService.update(competitionId, updatedCompetitionDto);
//        System.out.println("Updated Competition: " + updatedCompetition);

        //todo: delete competition
//        Long id = 1002L;
//        competitionService.delete(id);
//        System.out.println("Competition with ID " + id + " has been deleted.");

        //todo: get by id competition
        Long competitionId = 902L;
        CompetitionDtoReq competition = competitionService.getById(competitionId);
        System.out.println("Competition with ID " + competitionId + ":");
        System.out.println("Name: " + competition.name());
        System.out.println("Location: " + competition.location());
        System.out.println("Start Date: " + competition.startDate());
        System.out.println("End Date: " + competition.endDate());  }
}