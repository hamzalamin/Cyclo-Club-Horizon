package com.wora.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "riders")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "f_name" , nullable = false)
    private String fName;

    @NotBlank
    @Column(name = "l_name" , nullable = false)
    private String lName;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "nationality", nullable = false)
    private String nationality;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GeneralResult> generalResults;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoundResult> roundResults;

    public Rider() {
    }

    public Rider(Long id, String fName, String lName, LocalDate birthDate, String nationality, Team team, List<GeneralResult> generalResults, List<RoundResult> roundResults) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.team = team;
        this.generalResults = generalResults;
        this.roundResults = roundResults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getfName() {
        return fName;
    }

    public void setfName(@NotBlank String fName) {
        this.fName = fName;
    }

    public @NotBlank String getlName() {
        return lName;
    }

    public void setlName(@NotBlank String lName) {
        this.lName = lName;
    }

    public @NotNull LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotBlank String getNationality() {
        return nationality;
    }

    public void setNationality(@NotBlank String nationality) {
        this.nationality = nationality;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<GeneralResult> getGeneralResults() {
        return generalResults;
    }

    public void setGeneralResults(List<GeneralResult> generalResults) {
        this.generalResults = generalResults;
    }

    public List<RoundResult> getRoundResults() {
        return roundResults;
    }

    public void setRoundResults(List<RoundResult> roundResults) {
        this.roundResults = roundResults;
    }
}
