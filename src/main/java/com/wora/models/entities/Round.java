package com.wora.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @NotNull
    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne
    private Competition competition;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoundResult> roundResults;


    public Round(Long id, Integer stageNumber, LocalDate startDte, LocalDate endDte, Competition competition, List<RoundResult> roundResults) {
        this.id = id;
        this.stageNumber = stageNumber;
        this.startDte = startDte;
        this.endDte = endDte;
        this.competition = this.competition;
        this.roundResults = roundResults;
    }

}
