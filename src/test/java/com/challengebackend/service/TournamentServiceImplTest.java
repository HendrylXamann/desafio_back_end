package com.challengebackend.service;

import com.challengebackend.adapters.tournaments.payload.TournamentDTO;
import com.challengebackend.adapters.tournaments.payload.TournamentForm;
import com.challengebackend.adapters.tournaments.payload.TournamentPlayerForm;
import com.challengebackend.application.player.PlayersService;
import com.challengebackend.application.tournament.TournamentServiceImpl;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ReturnMessage;
import com.challengebackend.domain.player.Player;
import com.challengebackend.domain.tournament.Tournament;
import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import com.challengebackend.infrastructure.persistence.tournament.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TournamentServiceImplTest {

    @Mock
    private TournamentRepository tournamentsRepository;

    @Mock
    private PlayersService playersService;

    @InjectMocks
    private TournamentServiceImpl tournamentsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTournament() {
        TournamentForm form = new TournamentForm();
        form.setName("Tournament 1");
        form.setDate(LocalDate.parse("2023-10-01"));
        Tournament tournament = new Tournament(form.getName(), form.getDate());
        when(tournamentsRepository.existsByNameIgnoreCase(form.getName())).thenReturn(false);
        when(tournamentsRepository.save(any(Tournament.class))).thenReturn(tournament);

        TournamentDTO result = tournamentsService.createTournament(form);

        assertEquals(form.getName(), result.getName());
        verify(tournamentsRepository, times(1)).existsByNameIgnoreCase(form.getName());
        verify(tournamentsRepository, times(1)).save(any(Tournament.class));
    }

    @Test
    void testCreateTournamentAlreadyExists() {
        TournamentForm form = new TournamentForm();
        form.setName("Tournament 1");
        form.setDate(LocalDate.parse("2023-10-01"));
        when(tournamentsRepository.existsByNameIgnoreCase(form.getName())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tournamentsService.createTournament(form));

        assertEquals(MessageError.TOURNAMENT_ALREADY_EXISTS, exception.getMessageError());
        verify(tournamentsRepository, times(1)).existsByNameIgnoreCase(form.getName());
        verify(tournamentsRepository, never()).save(any(Tournament.class));
    }

    @Test
    void testFindAll() {
        Tournament tournament = new Tournament();
        Tournament tournament2 = new Tournament();
        tournament.setName("Tournament 1");
        tournament2.setName("Tournament 2");
        tournament.setDate(LocalDate.parse("2025-10-01"));
        tournament2.setDate(LocalDate.parse("2025-11-01"));

        List<Tournament> tournaments = List.of(tournament, tournament2);
        when(tournamentsRepository.findAll()).thenReturn(tournaments);

        List<TournamentDTO> result = tournamentsService.findAll();

        assertEquals(2, result.size());
        verify(tournamentsRepository, times(1)).findAll();
    }

    @Test
    void testAddPlayerToTournament() {
        Long tournamentId = 1L;
        TournamentPlayerForm form = new TournamentPlayerForm(1L);
        Tournament tournament = new Tournament();
        tournament.setName("Tournament 1");
        tournament.setDate(LocalDate.parse("2023-10-01"));
        Player player = new Player("John", 25, "USA");
        when(tournamentsRepository.findById(tournamentId)).thenReturn(Optional.of(tournament));
        when(playersService.getPlayerEntityById(form.playerId())).thenReturn(player);

        ReturnMessage result = tournamentsService.addPlayerToTournament(tournamentId, form);

        assertEquals(ReturnMessage.PLAYER_ADDED_SUCCESSFULLY, result);
        verify(tournamentsRepository, times(1)).findById(tournamentId);
        verify(playersService, times(1)).getPlayerEntityById(form.playerId());
        verify(tournamentsRepository, times(1)).save(tournament);
    }

    @Test
    void testAddPlayerToTournamentAlreadyRegistered() {
        Long tournamentId = 1L;
        TournamentPlayerForm form = new TournamentPlayerForm(1L);
        Tournament tournament = new Tournament();
        tournament.setName("Tournament 1");
        tournament.setDate(LocalDate.parse("2023-10-01"));
        Player player = new Player("John", 25, "USA");
        tournament.getPlayers().add(new PlayerTournament(player, tournament, 0));
        when(tournamentsRepository.findById(tournamentId)).thenReturn(Optional.of(tournament));
        when(playersService.getPlayerEntityById(form.playerId())).thenReturn(player);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tournamentsService.addPlayerToTournament(tournamentId, form));

        assertEquals(MessageError.PLAYER_ALREADY_REGISTERED_TOURNAMENT, exception.getMessageError());
        verify(tournamentsRepository, times(1)).findById(tournamentId);
        verify(playersService, times(1)).getPlayerEntityById(form.playerId());
        verify(tournamentsRepository, never()).save(tournament);
    }

    @Test
    void testRemovePlayerFromTournament() {
        Long tournamentId = 1L;
        TournamentPlayerForm form = new TournamentPlayerForm(1L);
        Tournament tournament = new Tournament();
        tournament.setName("Tournament 1");
        tournament.setDate(LocalDate.parse("2023-10-01"));
        Player player = new Player("John", 25, "USA");
        PlayerTournament playerTournament = new PlayerTournament(player, tournament, 0);
        tournament.getPlayers().add(playerTournament);
        when(tournamentsRepository.findById(tournamentId)).thenReturn(Optional.of(tournament));
        when(playersService.getPlayerEntityById(form.playerId())).thenReturn(player);

        ReturnMessage result = tournamentsService.removePlayerToTournament(tournamentId, form);

        assertEquals(ReturnMessage.PLAYER_DELETED_SUCCESSFULLY, result);
        verify(tournamentsRepository, times(1)).findById(tournamentId);
        verify(playersService, times(1)).getPlayerEntityById(form.playerId());
        verify(tournamentsRepository, times(1)).save(tournament);
    }

    @Test
    void testFinalizeTournament() {
        Long tournamentId = 1L;
        Tournament tournament = new Tournament();
        tournament.setName("Tournament 1");
        tournament.setDate(LocalDate.parse("2023-10-01"));

        when(tournamentsRepository.findById(tournamentId)).thenReturn(Optional.of(tournament));

        ReturnMessage result = tournamentsService.finalizeTournament(tournamentId);

        assertEquals(ReturnMessage.TOURNAMENT_FINISHED, result);
        assertTrue(tournament.isFinalized());
        verify(tournamentsRepository, times(1)).findById(tournamentId);
        verify(tournamentsRepository, times(1)).save(tournament);
    }
}