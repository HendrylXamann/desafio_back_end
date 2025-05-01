package com.challengebackend.adapters.players;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/players")
public interface PlayersAPI {

    @PostMapping
    ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerForm form);

    @GetMapping("/{id}")
    ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id);

    @GetMapping("/name")
    ResponseEntity<List<PlayerDTO>>  getPlayersByName(@Valid @RequestBody PlayerFilterForm form);

    @GetMapping("/all")
    ResponseEntity<List<PlayerDTO>> getAllPlayers();

    @PutMapping("/{id}")
    ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerFilterForm form);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable Long id);


}
