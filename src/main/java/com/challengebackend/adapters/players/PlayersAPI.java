package com.challengebackend.adapters.players;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. Players", description = "Endpoints for managing players")
@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/players")
public interface PlayersAPI {

    @Operation(summary = "Create a new player", description = "Registers a new player in the system")
    @PostMapping
    ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerForm form);

    @Operation(summary = "Get player by ID", description = "Fetches a player by their unique ID")
    @GetMapping("/{id}")
    ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id);

    @Operation(summary = "Search players by name", description = "Fetches players whose names match the given filter")
    @GetMapping("/name")
    ResponseEntity<List<PlayerDTO>> getPlayersByName(@Valid @RequestBody PlayerFilterForm form);

    @Operation(summary = "Get all players", description = "Fetches all registered players")
    @GetMapping("/all")
    ResponseEntity<List<PlayerDTO>> getAllPlayers();

    @Operation(summary = "Update player details", description = "Updates the details of an existing player")
    @PutMapping("/{id}")
    ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerFilterForm form);

    @Operation(summary = "Delete a player", description = "Deletes a player by their unique ID")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable Long id);
}
