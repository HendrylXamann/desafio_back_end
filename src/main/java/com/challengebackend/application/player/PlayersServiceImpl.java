package com.challengebackend.application.player;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerForm;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.common.exception.ResourceNotFoundException;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.challengebackend.infrastructure.persistence.player.PlayerRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService{
    private final PlayerRepository playerRepository;

    @Override
    public PlayerDTO createPlayer(PlayerForm form) {
        validatePlayerExists(form.getPlayerName());
        Player player = new Player(form.getPlayerName(), form.getPlayerAge(), form.getPlayerCountry());
        return new PlayerDTO(playerRepository.save(player));
    }

    @Override
    public PlayerDTO findById(Long id) {
        return new PlayerDTO(getPlayerEntityById(id));
    }

    @Override
    public List<PlayerDTO> findByName(String playerName) {
        List<Player> players = playerRepository.findByNameContainingIgnoreCase(playerName);
        return players.stream()
                .map(player -> new PlayerDTO(player.getId(), player.getName()))
                .toList();
    }

    @Override
    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream().map(PlayerDTO::new)
                .toList();
    }

    @Override
    public PlayerDTO updatePlayer(Long id, PlayerFilterForm form) {
        Player player = getPlayerEntityById(id);
        if (!player.getName().equals(form.getPlayerName())) validatePlayerExists(form.getPlayerName());
        if (!player.getTournaments().isEmpty()) {
            throw new IllegalArgumentException(MessageError.CANNOT_UPDATE_PLAYER_NAME_REGISTERED_TOURNAMENT);
        }

        player.setName(form.getPlayerName());
        if (form.getPlayerAge() != null) player.setAge(form.getPlayerAge());
        if (form.getPlayerCountry() != null) player.setCountry(form.getPlayerCountry());

        return new PlayerDTO(playerRepository.save(player));
    }

    @Override
    public ReturnMessage deletePlayer(Long id) {
        Player player = getPlayerEntityById(id);
        if (!player.getTournaments().isEmpty()) {
            throw new IllegalArgumentException(MessageError.CANNOT_DELETE_PLAYER_REGISTERED_TOURNAMENT);
        }

        playerRepository.deleteById(id);
        return ReturnMessage.PLAYER_DELETED_SUCCESSFULLY;
    }

    @Override
    public Player getPlayerEntityById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.PLAYER_NOT_FOUND));
    }

    private void validatePlayerExists(String name) {
        if (playerRepository.existsByNameIgnoreCase(name)) {
            throw new IllegalArgumentException(MessageError.PLAYER_ALREADY_EXISTS);
        }
    }
}
