package com.wora.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rounds")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Positive
    @Column(name = "stage_number", nullable = false)
    private Integer stageNumber;

    @NotNull
    @Column(name= "start_date", nullable = false)
    private LocalDate startDte;

    @NotNull
    @Column(name= "end_date", nullable = false)
    private LocalDate endDte;

    @ManyToOne
    private Competition competition;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoundResult> roundResults;

    public Round() {
    }

    public Round(Long id, Integer stageNumber, LocalDate startDte, LocalDate endDte, Competition competition, List<RoundResult> roundResults) {
        this.id = id;
        this.stageNumber = stageNumber;
        this.startDte = startDte;
        this.endDte = endDte;
        this.competition = this.competition;
        this.roundResults = roundResults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @Positive Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(@NotNull @Positive Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public @NotNull LocalDate getStartDte() {
        return startDte;
    }

    public void setStartDte(@NotNull LocalDate startDte) {
        this.startDte = startDte;
    }

    public @NotNull LocalDate getEndDte() {
        return endDte;
    }

    public void setEndDte(@NotNull LocalDate endDte) {
        this.endDte = endDte;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<RoundResult> getRoundResults() {
        return roundResults;
    }

    public void setRoundResults(List<RoundResult> roundResults) {
        this.roundResults = roundResults;
    }
}
