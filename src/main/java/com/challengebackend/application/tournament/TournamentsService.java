package com.challengebackend.application.tournament;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.common.valueobjects.ReturnMessage;

import java.util.List;

public interface TournamentsService {

    TournamentDTO createTournament(TournamentForm form);

    ReturnMessage addPlayerToTournament(Long tournamentId, TournamentPlayerForm form);

    ReturnMessage removePlayerToTournament(Long tournamentId, TournamentPlayerForm form);

    List<String> findAllPlayersByTournamentId(Long tournamentId);

    ReturnMessage finalizeTournament(Long tournamentId);
}
