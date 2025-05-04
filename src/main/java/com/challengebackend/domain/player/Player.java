package com.challengebackend.domain.player;

import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String country;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<PlayerTournament> tournaments = new HashSet<>();

    public Player() {
    }

    public Player(String name, Integer age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", tournaments=" + tournaments +
                '}';
    }
}
