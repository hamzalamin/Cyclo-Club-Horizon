package com.wora.models.entities;

import com.wora.models.entities.embeddables.GeneralResultId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;

@Entity
@Table(name = "general_results")
public class GeneralResult {
    @EmbeddedId
    private GeneralResultId id;

    @NotNull
    @Column(name = "general_time")
    private Duration generalTime;

    @NotNull
    @Column(name = "range")
    private Integer range;

    @ManyToOne
    @MapKey
    private Competition competition;

    @ManyToOne
    @MapKey
    private Rider rider;

    public GeneralResult() {}

    public GeneralResult(Competition competition, Rider rider) {
        this.id = new GeneralResultId(rider.getId(), competition.getId());
        this.competition = competition;
        this.rider = rider;
    }

    public GeneralResult(GeneralResultId id, Duration generalTime, Integer range, Competition competition, Rider rider) {
        this.id = id;
        this.generalTime = generalTime;
        this.range = range;
        this.competition = competition;
        this.rider = rider;
    }

    public GeneralResultId getId(GeneralResultId generalResultId) {
        return id;
    }

    public void setId(GeneralResultId id) {
        this.id = id;
    }

    public @NotNull Duration getGeneralTime() {
        return generalTime;
    }

    public void setGeneralTime(@NotNull Duration generalTime) {
        this.generalTime = generalTime;
    }

    public @NotNull Integer getRange() {
        return range;
    }

    public void setRange(@NotNull Integer range) {
        this.range = range;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
