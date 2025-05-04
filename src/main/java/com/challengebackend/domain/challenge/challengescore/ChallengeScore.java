package com.challengebackend.domain.challenge.challengescore;

import com.challengebackend.common.valueobjects.ChallengeTypes;
import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_type", nullable = false)
    private ChallengeTypes challengeType;
    private Integer score;

    public ChallengeScore() {
    }

    public ChallengeScore(PlayerTournament playerTournament, ChallengeTypes challengeType, Integer score) {
        this.playerTournament = playerTournament;
        this.challengeType = challengeType;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeScore that = (ChallengeScore) o;
        return Objects.equals(id, that.id) && Objects.equals(playerTournament, that.playerTournament) && challengeType == that.challengeType && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerTournament, challengeType, score);
    }

    @Override
    public String toString() {
        return "ChallengeScore{" +
                "id=" + id +
                ", playerTournament=" + playerTournament +
                ", challengeType=" + challengeType +
                ", score=" + score +
                '}';
    }
}
