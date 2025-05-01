package com.challengebackend.adapters.ranking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;

import java.util.List;

@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/ranking")
public interface RankingAPI {

    @GetMapping("/global")
    ResponseEntity<List<PlayerRankingDTO>> getGlobalRanking();

    @GetMapping("/tournament/{tournamentId}")
    ResponseEntity<List<PlayerRankingDTO>> getTournamentRanking(@PathVariable Long tournamentId);
}
