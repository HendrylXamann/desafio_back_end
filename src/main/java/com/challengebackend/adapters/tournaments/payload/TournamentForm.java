package com.challengebackend.adapters.tournaments.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TournamentForm {
    @NotNull
    private String name;
    @NotNull
    private LocalDate date;

}

