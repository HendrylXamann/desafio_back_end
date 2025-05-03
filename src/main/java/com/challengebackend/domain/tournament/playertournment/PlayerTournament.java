package com.challengebackend.domain.tournament.playertournment;

import com.challengebackend.domain.challenge.challengescore.ChallengeScore;
import com.challengebackend.domain.player.Player;
import com.challengebackend.domain.tournament.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
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
    private Player player;
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;
    private Integer score;
    @OneToMany(mappedBy = "playerTournament", cascade = CascadeType.ALL)
    private Set<ChallengeScore> challengeScores = new HashSet<>();


    public PlayerTournament() {
    }

    public PlayerTournament(Player player, Tournament tournament, Integer score) {
        this.player = player;
        this.tournament = tournament;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTournament that = (PlayerTournament) o;
        return Objects.equals(id, that.id) && Objects.equals(player, that.player) && Objects.equals(tournament, that.tournament) && Objects.equals(score, that.score) && Objects.equals(challengeScores, that.challengeScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player, tournament, score, challengeScores);
    }

    @Override
    public String toString() {
        return "PlayerTournament{" +
                "id=" + id +
                ", player=" + player +
                ", tournament=" + tournament +
                ", score=" + score +
                ", challengeScores=" + challengeScores +
                '}';
    }
}
