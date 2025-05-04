package com.challengebackend.controller;

import com.challengebackend.adapters.tournaments.TournamentsController;
import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.adapters.tournaments.payload.TournmentPlayerDTO;
import com.challengebackend.application.tournament.TournamentService;
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

class TournamentsControllerTest {

    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentsController tournamentsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTournament() {
        TournamentForm form = new TournamentForm();
        TournamentDTO expectedResponse = new TournamentDTO();
        when(tournamentService.createTournament(form)).thenReturn(expectedResponse);

        ResponseEntity<TournamentDTO> response = tournamentsController.createTournament(form);

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).createTournament(form);
    }

    @Test
    void testAllTournaments() {
        List<TournamentDTO> expectedResponse = Arrays.asList(new TournamentDTO(), new TournamentDTO());
        when(tournamentService.findAll()).thenReturn(expectedResponse);

        ResponseEntity<List<TournamentDTO>> response = tournamentsController.allTournaments();

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).findAll();
    }

    @Test
    void testAddPlayerToTournament() {
        Long tournamentId = 1L;
        TournamentPlayerForm form = new TournamentPlayerForm(tournamentId);
        ReturnMessage expectedResponse = ReturnMessage.PLAYER_ADDED_SUCCESSFULLY;
        when(tournamentService.addPlayerToTournament(tournamentId, form)).thenReturn(expectedResponse);

        ResponseEntity<?> response = tournamentsController.addPlayerToTournament(tournamentId, form);

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).addPlayerToTournament(tournamentId, form);
    }

    @Test
    void testRemovePlayerFromTournament() {
        Long tournamentId = 1L;
        TournamentPlayerForm form = new TournamentPlayerForm(tournamentId);
        ReturnMessage expectedResponse = ReturnMessage.PLAYER_REMOVED_SUCCESSFULLY;
        when(tournamentService.removePlayerToTournament(tournamentId, form)).thenReturn(expectedResponse);

        ResponseEntity<?> response = tournamentsController.removePlayerFromTournament(tournamentId, form);

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).removePlayerToTournament(tournamentId, form);
    }

    @Test
    void testListPlayersInTournament() {
        Long tournamentId = 1L;
        List<TournmentPlayerDTO> expectedResponse = Arrays.asList(new TournmentPlayerDTO(), new TournmentPlayerDTO());
        when(tournamentService.findAllPlayersByTournamentId(tournamentId)).thenReturn(expectedResponse);

        ResponseEntity<List<TournmentPlayerDTO>> response = tournamentsController.listPlayersInTournament(tournamentId);

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).findAllPlayersByTournamentId(tournamentId);
    }

    @Test
    void testFinalizeTournament() {
        Long tournamentId = 1L;
        ReturnMessage expectedResponse = ReturnMessage.TOURNAMENT_FINISHED;
        when(tournamentService.finalizeTournament(tournamentId)).thenReturn(expectedResponse);

        ResponseEntity<?> response = tournamentsController.finalizeTournament(tournamentId);

        assertEquals(expectedResponse, response.getBody());
        verify(tournamentService, times(1)).finalizeTournament(tournamentId);
    }
}
