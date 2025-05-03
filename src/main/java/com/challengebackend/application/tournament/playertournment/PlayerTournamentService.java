package com.challengebackend.application.tournament.playertournment;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;

import java.util.List;

public interface PlayerTournamentService {
    List<PlayerRankingDTO> getGlobalRanking();

    List<PlayerRankingDTO> getTournamentRanking(Long tournamentId);

    void registerScore(Long playerTournamentId, Long challengeId, Integer score);
}
