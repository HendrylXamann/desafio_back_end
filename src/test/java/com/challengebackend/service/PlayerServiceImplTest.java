package com.challengebackend.service;

import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import com.challengebackend.application.player.PlayersService;
import com.challengebackend.application.player.PlayersServiceImpl;
import com.challengebackend.common.exception.ResourceNotFoundException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.player.Player;
import com.challengebackend.infrastructure.persistence.player.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayersServiceImpl playersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlayer() {
        PlayerForm form = new PlayerForm();
        form.setPlayerName("Johnie");
        form.setPlayerAge(25);
        form.setPlayerCountry("Japan");
        Player player = new Player("Johnie", 25, "Japan");
        when(playerRepository.existsByNameIgnoreCase(form.getPlayerName())).thenReturn(false);
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        PlayerDTO result = playersService.createPlayer(form);

        assertEquals(form.getPlayerName(), result.getPlayerName());
        verify(playerRepository, times(1)).existsByNameIgnoreCase(form.getPlayerName());
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void testCreatePlayerAlreadyExists() {
        PlayerForm form = new PlayerForm();
        form.setPlayerName("Johnie");
        form.setPlayerAge(25);
        form.setPlayerCountry("Japan");
        when(playerRepository.existsByNameIgnoreCase(form.getPlayerName())).thenReturn(true);

        com.challengebackend.common.exception.IllegalArgumentException exception = assertThrows(com.challengebackend.common.exception.IllegalArgumentException.class, () -> playersService.createPlayer(form));

        assertEquals(MessageError.PLAYER_ALREADY_EXISTS, exception.getMessageError());

        verify(playerRepository, times(1)).existsByNameIgnoreCase(form.getPlayerName());
        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Player player = new Player("John", 25, "USA");
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        PlayerDTO result = playersService.findById(id);

        assertEquals(player.getName(), result.getPlayerName());
        verify(playerRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> playersService.findById(id));

        assertEquals(MessageError.PLAYER_NOT_FOUND, exception.getMessageError()); // <-- aqui
        verify(playerRepository, times(1)).findById(id);
    }


    @Test
    void testFindByName() {
        String name = "John";
        List<Player> players = Arrays.asList(new Player("John", 25, "USA"), new Player("Johnny", 30, "Canada"));
        when(playerRepository.findByNameContainingIgnoreCase(name)).thenReturn(players);

        List<PlayerDTO> result = playersService.findByName(name);

        assertEquals(2, result.size());
        verify(playerRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    @Test
    void testFindAll() {
        List<Player> players = Arrays.asList(new Player("John", 25, "USA"), new Player("Jane", 28, "UK"));
        when(playerRepository.findAll()).thenReturn(players);

        List<PlayerDTO> result = playersService.findAll();

        assertEquals(2, result.size());
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePlayer() {
        Long id = 1L;
        Player player = new Player("John", 25, "USA");
        PlayerFilterForm form = new PlayerFilterForm();
        form.setPlayerName("Johnny");
        form.setPlayerAge(30);
        form.setPlayerCountry("Brasil");
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));
        when(playerRepository.existsByNameIgnoreCase(form.getPlayerName())).thenReturn(false);
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        PlayerDTO result = playersService.updatePlayer(id, form);

        assertEquals(form.getPlayerName(), result.getPlayerName());
        verify(playerRepository, times(1)).findById(id);
        verify(playerRepository, times(1)).existsByNameIgnoreCase(form.getPlayerName());
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void testDeletePlayer() {
        Long id = 1L;
        Player player = new Player("John", 25, "USA");
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        ReturnMessage result = playersService.deletePlayer(id);

        assertEquals(ReturnMessage.PLAYER_DELETED_SUCCESSFULLY, result);
        verify(playerRepository, times(1)).findById(id);
        verify(playerRepository, times(1)).deleteById(id);
    }
}
