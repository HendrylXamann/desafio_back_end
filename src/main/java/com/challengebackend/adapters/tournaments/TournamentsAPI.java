package com.challengebackend.adapters.tournaments;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.adapters.tournaments.payload.TournmentPlayerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "2. Tournaments", description = "Endpoints for managing tournaments")
@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/tournaments")
public interface TournamentsAPI {

    @Operation(summary = "Create a new tournament", description = "Registers a new tournament in the system")
    @PostMapping
    ResponseEntity<TournamentDTO> createTournament(@Valid @RequestBody TournamentForm form);

    @Operation(summary = "Add a player to a tournament", description = "Adds a player to a specific tournament")
    @PostMapping("/{tournamentId}/add-player")
    ResponseEntity<?> addPlayerToTournament(@PathVariable Long tournamentId, @Valid @RequestBody TournamentPlayerForm form);

    @Operation(summary = "Remove a player from a tournament", description = "Removes a player from a specific tournament")
    @DeleteMapping("/{tournamentId}/remove-player")
    ResponseEntity<?> removePlayerFromTournament(@PathVariable Long tournamentId, @Valid @RequestBody  TournamentPlayerForm form);

    @Operation(summary = "List players in a tournament", description = "Fetches all players participating in a specific tournament")
    @GetMapping("/{tournamentId}/all-players")
    ResponseEntity<List<TournmentPlayerDTO>> listPlayersInTournament(@PathVariable Long tournamentId);

    @Operation(summary = "Finalize a tournament", description = "Marks a tournament as finalized")
    @PutMapping("/{tournamentId}/finalize")
    ResponseEntity<?> finalizeTournament(@PathVariable Long tournamentId);

    @Operation(summary = "List all tournaments", description = "Fetches all tournaments in the system")
    @GetMapping("/all")
    ResponseEntity<List<TournamentDTO>> allTournaments();
}
