package com.challengebackend.adapters.ranking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;

import java.util.List;

@Tag(name = "4. Ranking", description = "Endpoints for retrieving player rankings")
@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/ranking")
public interface RankingAPI {

    @Operation(summary = "Get global ranking", description = "Fetches the global ranking of all players")
    @GetMapping("/global")
    ResponseEntity<List<PlayerRankingDTO>> getGlobalRanking();

    @Operation(summary = "Get tournament ranking", description = "Fetches the ranking of players in a specific tournament")
    @GetMapping("/tournament/{tournamentId}")
    ResponseEntity<List<PlayerRankingDTO>> getTournamentRanking(@PathVariable Long tournamentId);
}
