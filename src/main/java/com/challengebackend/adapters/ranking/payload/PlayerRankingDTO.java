package com.challengebackend.adapters.ranking.payload;

import lombok.Data;

@Data
public class PlayerRankingDTO {
    private Integer positionRanking;
    private String playerName;
    private Integer score;

    public PlayerRankingDTO(String playerName, Integer score, Integer positionRanking) {
        this.positionRanking = positionRanking;
        this.playerName = playerName;
        this.score = score;

    }
    public PlayerRankingDTO(String playerName, Integer score) {
        this.playerName = playerName;
        this.score = score;

    }
}
