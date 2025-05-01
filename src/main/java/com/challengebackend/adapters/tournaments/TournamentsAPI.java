package com.challengebackend.adapters.tournaments;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/tournaments")
public interface TournamentsAPI {

    @PostMapping
    ResponseEntity<TournamentDTO> createTournament(@Valid @RequestBody TournamentForm form);

    @PostMapping("/{tournamentId}/add-player")
    ResponseEntity<?> addPlayerToTournament(@PathVariable Long tournamentId, @Valid @RequestBody TournamentPlayerForm form);

    @DeleteMapping("/{tournamentId}/remove-player")
    ResponseEntity<?> removePlayerFromTournament(@PathVariable Long tournamentId, @Valid @RequestBody  TournamentPlayerForm form);

    @GetMapping("/{tournamentId}/all-players")
    ResponseEntity<List<String>> listPlayersInTournament(@PathVariable Long tournamentId);

    @PutMapping("/{tournamentId}/finalize")
    ResponseEntity<?> finalizeTournament(@PathVariable Long tournamentId);
}
