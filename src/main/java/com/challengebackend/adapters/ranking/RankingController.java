package com.challengebackend.adapters.ranking;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.challengebackend.application.ranking.RankingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankingController implements RankingAPI {
    private final RankingService rankingService;

    @Override
    public ResponseEntity<List<PlayerRankingDTO>> getGlobalRanking() {
        return ResponseEntity.ok(rankingService.getGlobalRanking());
    }
    @Override
    public ResponseEntity<List<PlayerRankingDTO>> getTournamentRanking(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(rankingService.getTournamentRanking(tournamentId));
    }
}
