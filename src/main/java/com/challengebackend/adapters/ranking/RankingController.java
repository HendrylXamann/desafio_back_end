package com.challengebackend.adapters.ranking;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.application.tournament.playertournment.PlayerTournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankingController implements RankingAPI {
    private final PlayerTournamentService playerTournamentService;


    @Override
    public ResponseEntity<List<PlayerRankingDTO>> getGlobalRanking() {
        return ResponseEntity.ok(playerTournamentService.getGlobalRanking());
    }
    @Override
    public ResponseEntity<List<PlayerRankingDTO>> getTournamentRanking(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(playerTournamentService.getTournamentRanking(tournamentId));
    }
}
