package com.challengebackend.application.tournaments;

import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.domain.challenges.Challenges;
import com.challengebackend.domain.tournaments.PlayerTournament;
import com.challengebackend.infrastructure.persistence.challenges.ChallengesRepository;
import com.challengebackend.domain.challenges.ChallengeScore;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.infrastructure.persistence.tournaments.PlayerTournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerTournamentServiceImpl implements PlayerTournamentService{
    private final PlayerTournamentRepository playerTournamentRepository;
    private final ChallengesRepository challengeRepository;

    @Override
    public List<PlayerRankingDTO> getGlobalRanking() {
        return playerTournamentRepository.findAll().stream()
                .map(playerTournament -> new PlayerRankingDTO(
                        playerTournament.getPlayer().getName(),
                        playerTournament.getScore()
                ))
                .sorted((a, b) -> b.getScore().compareTo(a.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerRankingDTO> getTournamentRanking(Long tournamentId) {
        return playerTournamentRepository.findByTournamentId(tournamentId).stream()
                .map(playerTournament -> new PlayerRankingDTO(
                        playerTournament.getPlayer().getName(),
                        playerTournament.getScore()
                ))
                .sorted((a, b) -> b.getScore().compareTo(a.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public void registerScore(Long playerTournamentId, Long challengeId, Integer score) {
        PlayerTournament playerTournament = playerTournamentRepository.findById(playerTournamentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageError.PLAYER_TOURNAMENT_NOT_FOUND));

        Challenges challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException(MessageError.CHALLENGE_NOT_FOUND));

        ChallengeScore challengeScore = new ChallengeScore(playerTournament, challenge, score);

        playerTournament.getChallengeScores().add(challengeScore);
        playerTournament.setScore(playerTournament.getScore() + score);

        playerTournamentRepository.save(playerTournament);
    }
}
