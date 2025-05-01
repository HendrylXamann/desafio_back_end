package com.challengebackend.application.ranking;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.application.tournaments.PlayerTournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{
    private final PlayerTournamentService playerTournamentService;

    @Override
    public List<PlayerRankingDTO> getGlobalRanking() {
        return playerTournamentService.getGlobalRanking();
    }

    @Override
    public List<PlayerRankingDTO> getTournamentRanking(Long tournamentId) {
        return playerTournamentService.getTournamentRanking(tournamentId);
    }

    @Override
    public void registerScore(Long playerTournamentId, Long challengeId, Integer score) {
    }
}
