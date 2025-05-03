package com.challengebackend.adapters.players.payload;

import com.challengebackend.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String playerName;
    private Integer playerAge;
    private String playerCountry;
    private List<String> playerTournaments;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.playerName = player.getName();
        this.playerAge = player.getAge();
        this.playerCountry = player.getCountry();
        this.playerTournaments = player.getTournaments().stream()
                .map(tournament -> tournament.getTournament().getName())
                .toList();
    }

    public PlayerDTO(Long id, String playerName) {
        this.id = id;
        this.playerName = playerName;
    }

}