package com.wora.models.entities;

import com.wora.models.dtos.generalResult.EmbeddedGeneralResultDto;
import com.wora.models.dtos.round.EmbeddedRoundDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "location" ,nullable = false, length = 255)
    private String location;

    @NotNull
    @Column(name = "is_closed")
    private Boolean isClosed;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GeneralResult> generalResults;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Round> rounds;

    public Competition(Long id, String name, LocalDate startDate, LocalDate endDate, String location, List<GeneralResult> generalResults, List<Round> rounds) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.generalResults = generalResults;
        this.rounds = rounds;
    }

    public Competition(String name, LocalDate startDate, LocalDate endDate, String location, List<GeneralResult> generalResults, List<Round> rounds) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.generalResults = generalResults;
        this.rounds = rounds;
    }

    public Competition(String name, LocalDate startDate, LocalDate endDate, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

}
