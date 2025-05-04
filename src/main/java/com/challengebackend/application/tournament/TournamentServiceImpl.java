package com.challengebackend.application.tournament;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.adapters.tournaments.payload.TournmentPlayerDTO;
import com.challengebackend.application.player.PlayersService;
import com.challengebackend.common.exception.ResourceNotFoundException;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.player.Player;
import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import com.challengebackend.domain.tournament.Tournament;
import com.challengebackend.infrastructure.persistence.tournament.TournamentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentsRepository;
    private final PlayersService playersService;

    @Override
    public TournamentDTO createTournament(TournamentForm form) {
        validateTournamentsExists(form.getName());
        Tournament tournament = new Tournament(form.getName(), form.getDate());
        return new TournamentDTO(tournamentsRepository.save(tournament));
    }

    @Override
    public List<TournamentDTO> findAll() {
        return tournamentsRepository.findAll()
                .stream().map(TournamentDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public ReturnMessage addPlayerToTournament(Long tournamentId, TournamentPlayerForm form) {
        Tournament tournamentFind = getTournamentsEntityById(tournamentId);
        validateTournamentAlreadyFinalized(tournamentFind);
        Player player = playersService.getPlayerEntityById(form.playerId());

        boolean alreadyInTournament = tournamentFind.getPlayers().stream()
                .anyMatch(playerTournament -> playerTournament.getPlayer().equals(player));

        if (alreadyInTournament) {
            throw new IllegalArgumentException(MessageError.PLAYER_ALREADY_REGISTERED_TOURNAMENT);
        }

        PlayerTournament playerTournament = new PlayerTournament(player, tournamentFind, 0);
        tournamentFind.getPlayers().add(playerTournament);

        tournamentsRepository.save(tournamentFind);
        return ReturnMessage.PLAYER_ADDED_SUCCESSFULLY;
    }

    @Override
    @Transactional
    public ReturnMessage removePlayerToTournament(Long tournamentId, TournamentPlayerForm form) {
        Tournament tournamentFind = getTournamentsEntityById(tournamentId);
        Player player = playersService.getPlayerEntityById(form.playerId());

        PlayerTournament playerTournament = tournamentFind.getPlayers().stream()
                .filter(pt -> pt.getPlayer().equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageError.PLAYER_NOT_FOUND_IN_TOURNAMENT));

        tournamentFind.getPlayers().remove(playerTournament);

        tournamentsRepository.save(tournamentFind);
        return ReturnMessage.PLAYER_DELETED_SUCCESSFULLY;
    }

    @Override
    public List<TournmentPlayerDTO> findAllPlayersByTournamentId(Long tournamentId) {
        Tournament tournamentFind = getTournamentsEntityById(tournamentId);
        return tournamentFind.getPlayers().stream()
                .map(playerTournament -> new TournmentPlayerDTO(
                        playerTournament.getPlayer().getId(),
                        playerTournament.getPlayer().getName(),
                        new TournamentDTO(tournamentFind)
                ))
                .toList();
    }

    @Override
    public ReturnMessage finalizeTournament(Long tournamentId) {
        Tournament tournamentFind = getTournamentsEntityById(tournamentId);
        validateTournamentAlreadyFinalized(tournamentFind);

        tournamentFind.setFinalized(true);
        tournamentsRepository.save(tournamentFind);

        return ReturnMessage.TOURNAMENT_FINISHED;
    }

    private Tournament getTournamentsEntityById(Long id) {
        return tournamentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.TOURNAMENT_NOT_FOUND));
    }

    private void validateTournamentsExists(String name) {
        if (tournamentsRepository.existsByNameIgnoreCase(name)) {
            throw new com.challengebackend.common.exception.IllegalArgumentException(MessageError.TOURNAMENT_ALREADY_EXISTS);
        }
    }

    private void validateTournamentAlreadyFinalized(Tournament tournament) {
        if (tournament.isFinalized()) {
            throw new IllegalArgumentException(MessageError.TOURNAMENT_ALREADY_FINALIZED);
        }
    }
}
