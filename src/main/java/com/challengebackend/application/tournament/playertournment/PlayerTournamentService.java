package com.challengebackend.application.tournament.playertournment;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.common.valueobjects.ChallengeTypes;

import java.util.List;

public interface PlayerTournamentService {
    List<PlayerRankingDTO> getGlobalRanking();

    List<PlayerRankingDTO> getTournamentRanking(Long tournamentId);

    void registerScore(Long playerTournamentId, ChallengeTypes challengeType, Integer score);
}
