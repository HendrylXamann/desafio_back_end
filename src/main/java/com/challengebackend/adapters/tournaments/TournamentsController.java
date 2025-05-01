package com.challengebackend.adapters.tournaments;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.application.tournaments.TournamentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TournamentsController implements TournamentsAPI {
    private final TournamentsService tournamentsService;

    @Override
    public ResponseEntity<TournamentDTO> createTournament(TournamentForm form) {
        return ResponseEntity.ok(tournamentsService.createTournament(form));
    }

    @Override
    public ResponseEntity<?> addPlayerToTournament(Long tournamentId, TournamentPlayerForm form) {
        return ResponseEntity.ok(tournamentsService.addPlayerToTournament(tournamentId, form));
    }

    @Override
    public ResponseEntity<?>  removePlayerFromTournament(Long tournamentId, TournamentPlayerForm form) {
        return ResponseEntity.ok(tournamentsService.removePlayerToTournament(tournamentId, form));
    }

    @Override
    public ResponseEntity<List<String>> listPlayersInTournament(Long tournamentId) {
        return ResponseEntity.ok(tournamentsService.findAllPlayersByTournamentId(tournamentId));
    }

    @Override
    public ResponseEntity<?> finalizeTournament(Long tournamentId) {
        return ResponseEntity.ok(tournamentsService.finalizeTournament(tournamentId));
    }
}
