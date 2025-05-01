package com.challengebackend.application.ranking;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;

import java.util.List;

public interface RankingService {
    List<PlayerRankingDTO> getGlobalRanking();

    List<PlayerRankingDTO> getTournamentRanking(Long tournamentId);

    void registerScore(Long playerTournamentId, Long challengeId, Integer score);
}
