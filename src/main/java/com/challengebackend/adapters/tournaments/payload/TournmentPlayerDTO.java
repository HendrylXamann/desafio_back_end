package com.challengebackend.adapters.tournaments.payload;

import lombok.Data;

@Data
public class TournmentPlayerDTO {
    private Long id;
    private String name;
    private TournamentDTO tournament;

    public TournmentPlayerDTO() {
    }

    public TournmentPlayerDTO(Long id, String name, TournamentDTO tournament) {
        this.id = id;
        this.name = name;
        this.tournament = tournament;
    }
}
