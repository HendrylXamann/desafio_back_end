package com.challengebackend.domain.challenge.challengescore;

import com.challengebackend.domain.challenge.Challenge;
import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "challenge_score")
public class ChallengeScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_tournament_id", nullable = false)
    private PlayerTournament playerTournament;
    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;
    private Integer score;

    public ChallengeScore() {
    }

    public ChallengeScore(PlayerTournament playerTournament, Challenge challenge, Integer score) {
        this.playerTournament = playerTournament;
        this.challenge = challenge;
        this.score = score;
    }
}
