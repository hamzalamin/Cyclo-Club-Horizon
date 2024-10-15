package com.wora.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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

    @NotBlank
    @Column(name = "team", nullable = false)
    private String team;

    public Rider() {}

    public Rider(Long id, String fName, String lName, LocalDate birthDate, String nationality, String team) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.team = team;
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

    public @NotBlank String getTeam() {
        return team;
    }

    public void setTeam(@NotBlank String team) {
        this.team = team;
    }
}
