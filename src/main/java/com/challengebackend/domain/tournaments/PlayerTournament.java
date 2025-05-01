package com.challengebackend.domain.tournaments;

import com.challengebackend.domain.challenges.ChallengeScore;
import com.challengebackend.domain.players.Players;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "player_tournament")
public class PlayerTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Players player;
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournaments tournament;
    private Integer score;
    @OneToMany(mappedBy = "playerTournament", cascade = CascadeType.ALL)
    private Set<ChallengeScore> challengeScores = new HashSet<>();


    public PlayerTournament() {
    }

    public PlayerTournament(Players player, Tournaments tournament, Integer score) {
        this.player = player;
        this.tournament = tournament;
        this.score = score;
    }
}
