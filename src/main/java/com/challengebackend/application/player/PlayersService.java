package com.challengebackend.application.player;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.player.Player;

import java.util.List;

public interface PlayersService {

    PlayerDTO createPlayer(PlayerForm form);

    PlayerDTO findById(Long id);

    PlayerDTO updatePlayer(Long id, PlayerFilterForm form);

    ReturnMessage deletePlayer(Long id);

    List<PlayerDTO> findByName(PlayerFilterForm form);

    List<PlayerDTO> findAll();

    Player getPlayerEntityById(Long id);
}
