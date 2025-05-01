package com.challengebackend.domain.players;

import com.challengebackend.domain.tournaments.PlayerTournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String country;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<PlayerTournament> tournaments = new HashSet<>();

    public Players() {
    }

    public Players(String name, Integer age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players = (Players) o;
        return Objects.equals(id, players.id) && Objects.equals(name, players.name) && Objects.equals(tournaments, players.tournaments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tournaments);
    }

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tournaments=" + tournaments +
                '}';
    }
}
