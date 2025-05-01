package com.challengebackend.application.tournaments;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.application.players.PlayersService;
import com.challengebackend.common.exception.ResourceNotFoundException;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.players.Players;
import com.challengebackend.domain.tournaments.PlayerTournament;
import com.challengebackend.domain.tournaments.Tournaments;
import com.challengebackend.infrastructure.persistence.tournaments.TournamentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentsServiceImpl implements TournamentsService {
    private final TournamentsRepository tournamentsRepository;
    private final PlayersService playersService;

    @Override
    public TournamentDTO createTournament(TournamentForm form) {
        validateTournamentsExists(form.getName());
        Tournaments tournament = new Tournaments(form.getName(), form.getDate());
        return new TournamentDTO(tournamentsRepository.save(tournament));
    }

    @Override
    public ReturnMessage addPlayerToTournament(Long tournamentId, TournamentPlayerForm form) {
        Tournaments tournamentFind = getTournamentsEntityById(tournamentId);
        validateTournamentAlreadyFinalized(tournamentFind);
        Players player = playersService.getPlayerEntityById(form.playerId());

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
    public ReturnMessage removePlayerToTournament(Long tournamentId, TournamentPlayerForm form) {
        Tournaments tournamentFind = getTournamentsEntityById(tournamentId);
        Players player = playersService.getPlayerEntityById(form.playerId());

        PlayerTournament playerTournament = tournamentFind.getPlayers().stream()
                .filter(pt -> pt.getPlayer().equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageError.PLAYER_NOT_FOUND_IN_TOURNAMENT));

        tournamentFind.getPlayers().remove(playerTournament);

        tournamentsRepository.save(tournamentFind);
        return ReturnMessage.PLAYER_DELETED_SUCCESSFULLY;
    }

    @Override
    public List<String> findAllPlayersByTournamentId(Long tournamentId) {
        Tournaments tournamentFind = getTournamentsEntityById(tournamentId);
        return tournamentFind.getPlayers().stream()
                .map(playerTournament -> playerTournament.getPlayer().getName())
                .toList();
    }

    @Override
    public ReturnMessage finalizeTournament(Long tournamentId) {
        Tournaments tournamentFind = getTournamentsEntityById(tournamentId);
        validateTournamentAlreadyFinalized(tournamentFind);

        tournamentFind.setFinalized(true);
        tournamentsRepository.save(tournamentFind);

        return ReturnMessage.TOURNAMENT_FINISHED;
    }

    private Tournaments getTournamentsEntityById(Long id) {
        return tournamentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.TOURNAMENT_NOT_FOUND));
    }

    private void validateTournamentsExists(String name) {
        if (tournamentsRepository.existsByNameIgnoreCase(name)) {
            throw new com.challengebackend.common.exception.IllegalArgumentException(MessageError.TOURNAMENT_ALREADY_EXISTS);
        }
    }

    private void validateTournamentAlreadyFinalized(Tournaments tournament) {
        if (tournament.isFinalized()) {
            throw new IllegalArgumentException(MessageError.TOURNAMENT_ALREADY_FINALIZED);
        }
    }
}
