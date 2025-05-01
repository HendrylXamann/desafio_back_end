package com.challengebackend.adapters.tournaments.payload;

import com.challengebackend.domain.tournaments.Tournaments;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TournamentDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private boolean isFinalized;

    public TournamentDTO() {
    }

    public TournamentDTO(Tournaments tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.date = tournament.getDate();
        this.isFinalized = tournament.isFinalized();
    }

}
