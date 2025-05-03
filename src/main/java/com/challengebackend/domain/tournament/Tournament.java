package com.challengebackend.domain.tournament;

import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private boolean isFinalized = false;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<PlayerTournament> players = new HashSet<>();

    public Tournament() {
    }

    public Tournament(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return isFinalized == that.isFinalized && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, isFinalized, players);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isFinalized=" + isFinalized +
                ", players=" + players +
                '}';
    }
}
