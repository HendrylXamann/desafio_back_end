package com.challengebackend.domain.tournaments;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tournaments")
public class Tournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private boolean isFinalized = false;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<PlayerTournament> players = new HashSet<>();

    public Tournaments() {
    }

    public Tournaments(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }
}
