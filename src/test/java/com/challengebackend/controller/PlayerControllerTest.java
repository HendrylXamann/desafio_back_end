package com.challengebackend.controller;

import com.challengebackend.adapters.players.PlayersController;
import com.challengebackend.adapters.players.payload.PlayerDTO;
import com.challengebackend.adapters.players.payload.PlayerFilterForm;
import com.challengebackend.adapters.players.payload.PlayerForm;
import com.challengebackend.application.player.PlayersService;
import com.challengebackend.common.valueobjects.ReturnMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PlayerControllerTest {
    @Mock
    private PlayersService playersService;

    @InjectMocks
    private PlayersController playersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlayer() {
        PlayerForm form = new PlayerForm();
        PlayerDTO expectedResponse = new PlayerDTO();
        when(playersService.createPlayer(form)).thenReturn(expectedResponse);

        ResponseEntity<PlayerDTO> response = playersController.createPlayer(form);

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).createPlayer(form);
    }

    @Test
    void testGetPlayerById() {
        Long id = 1L;
        PlayerDTO expectedResponse = new PlayerDTO();
        when(playersService.findById(id)).thenReturn(expectedResponse);

        ResponseEntity<PlayerDTO> response = playersController.getPlayerById(id);

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).findById(id);
    }

    @Test
    void testGetPlayersByName() {
        String playerName = "John";
        List<PlayerDTO> expectedResponse = Arrays.asList(new PlayerDTO(), new PlayerDTO());
        when(playersService.findByName(playerName)).thenReturn(expectedResponse);

        ResponseEntity<List<PlayerDTO>> response = playersController.getPlayersByName(playerName);

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).findByName(playerName);
    }

    @Test
    void testGetAllPlayers() {
        List<PlayerDTO> expectedResponse = Arrays.asList(new PlayerDTO(), new PlayerDTO());
        when(playersService.findAll()).thenReturn(expectedResponse);

        ResponseEntity<List<PlayerDTO>> response = playersController.getAllPlayers();

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).findAll();
    }

    @Test
    void testUpdatePlayer() {
        Long id = 1L;
        PlayerFilterForm form = new PlayerFilterForm();
        PlayerDTO expectedResponse = new PlayerDTO();
        when(playersService.updatePlayer(id, form)).thenReturn(expectedResponse);

        ResponseEntity<PlayerDTO> response = playersController.updatePlayer(id, form);

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).updatePlayer(id, form);
    }

    @Test
    void testDeletePlayer() {
        Long id = 1L;
        ReturnMessage expectedResponse = ReturnMessage.PLAYER_DELETED_SUCCESSFULLY;
        when(playersService.deletePlayer(id)).thenReturn(expectedResponse);

        ResponseEntity<?> response = playersController.deletePlayer(id);

        assertEquals(expectedResponse, response.getBody());
        verify(playersService, times(1)).deletePlayer(id);
    }

}
