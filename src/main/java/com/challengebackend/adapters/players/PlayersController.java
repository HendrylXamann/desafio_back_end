package com.challengebackend.adapters.players;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import com.challengebackend.application.player.PlayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayersController implements PlayersAPI{
    private final PlayersService playersService;

    @Override
    public ResponseEntity<PlayerDTO> createPlayer(PlayerForm form) {
        return ResponseEntity.ok(playersService.createPlayer(form));
    }

    @Override
    public ResponseEntity<PlayerDTO> getPlayerById(Long id) {
        return ResponseEntity.ok(playersService.findById(id));
    }

    @Override
    public ResponseEntity<List<PlayerDTO>> getPlayersByName(PlayerFilterForm form) {
        return ResponseEntity.ok(playersService.findByName(form));
    }

    @Override
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playersService.findAll());
    }

    @Override
    public ResponseEntity<PlayerDTO> updatePlayer(Long id, PlayerFilterForm form) {
        return ResponseEntity.ok(playersService.updatePlayer(id, form));
    }

    @Override
    public ResponseEntity<?> deletePlayer(Long id) {
        return ResponseEntity.ok(playersService.deletePlayer(id));
    }
}
