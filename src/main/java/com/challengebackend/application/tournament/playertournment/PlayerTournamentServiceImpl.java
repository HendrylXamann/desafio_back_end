package com.challengebackend.application.tournament.playertournment;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ChallengeTypes;
import com.challengebackend.domain.player.Player;
import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import com.challengebackend.domain.challenge.challengescore.ChallengeScore;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.infrastructure.persistence.tournament.PlayerTournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayerTournamentServiceImpl implements PlayerTournamentService{
    private final PlayerTournamentRepository playerTournamentRepository;

    @Override
    public List<PlayerRankingDTO> getGlobalRanking() {
        List<PlayerTournament> playerTournaments = playerTournamentRepository.findAll();
        return orderByScore(playerTournaments);
    }

    @Override
    public List<PlayerRankingDTO> getTournamentRanking(Long tournamentId) {
        List<PlayerTournament> playerTournaments = playerTournamentRepository.findByTournamentId(tournamentId);
        return orderByScore(playerTournaments);
    }

    @Override
    public void registerScore(Long playerTournamentId, ChallengeTypes challengeType, Integer score) {
        PlayerTournament playerTournament = playerTournamentRepository.findByPlayer_id(playerTournamentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageError.PLAYER_TOURNAMENT_NOT_FOUND));

        ChallengeScore challengeScore = new ChallengeScore(playerTournament, challengeType, score);

        playerTournament.getChallengeScores().add(challengeScore);
        playerTournament.setScore(playerTournament.getScore() + score);

        playerTournamentRepository.save(playerTournament);
    }

    private List<PlayerRankingDTO> orderByScore(List<PlayerTournament> playerTournaments) {
        Map<Player, Integer> totalScoresByPlayer = new HashMap<>();
        for (PlayerTournament pt : playerTournaments) {
            int totalScore = pt.getChallengeScores()
                    .stream()
                    .mapToInt(ChallengeScore::getScore)
                    .sum();
            totalScoresByPlayer.put(pt.getPlayer(), totalScore);
        }

        List<Map.Entry<Player, Integer>> sortedList = totalScoresByPlayer.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .toList();

        List<PlayerRankingDTO> rankingOrdered = new ArrayList<>();
        int position = 1;
        for (Map.Entry<Player, Integer> entry : sortedList) {
            Player player = entry.getKey();
            Integer score = entry.getValue();
            rankingOrdered.add(new PlayerRankingDTO(player.getName(), score, position++));
        }
        return rankingOrdered;
    }
}
