package com.wora.models.entities;

import com.wora.models.entities.embeddables.RoundResultId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Duration;

@Entity
@Table(name = "round_result")
public class RoundResult {

    @EmbeddedId
    private RoundResultId id;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Duration duration;

    @NotNull
    @Column(name = "position", nullable = false)
    @Positive
    private Integer position;

    @ManyToOne
    @MapsId("roundId")
    private Round round;

    @ManyToOne
    @MapsId("riderId")
    private Rider rider;

    public RoundResult() {
    }

    public RoundResult(RoundResultId id, Duration duration, Integer position, Round round, Rider rider) {
        this.id = id;
        this.duration = duration;
        this.position = position;
        this.round = round;
        this.rider = rider;
    }

    public RoundResultId getId() {
        return id;
    }

    public void setId(RoundResultId id) {
        this.id = id;
    }

    public @NotNull Duration getDuration() {
        return duration;
    }

    public void setDuration(@NotNull Duration duration) {
        this.duration = duration;
    }

    public @NotNull @Positive Integer getPosition() {
        return position;
    }

    public void setPosition(@NotNull @Positive Integer position) {
        this.position = position;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
