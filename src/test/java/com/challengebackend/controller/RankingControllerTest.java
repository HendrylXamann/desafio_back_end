package com.challengebackend.controller;

import com.challengebackend.adapters.ranking.RankingController;
import com.challengebackend.adapters.ranking.payload.PlayerRankingDTO;
import com.challengebackend.application.tournament.playertournment.PlayerTournamentService;
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

public class RankingControllerTest {

    @Mock
    private PlayerTournamentService playerTournamentService;

    @InjectMocks
    private RankingController rankingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGlobalRanking() {
        List<PlayerRankingDTO> expectedResponse = Arrays.asList(new PlayerRankingDTO(), new PlayerRankingDTO());
        when(playerTournamentService.getGlobalRanking()).thenReturn(expectedResponse);

        ResponseEntity<List<PlayerRankingDTO>> response = rankingController.getGlobalRanking();

        assertEquals(expectedResponse, response.getBody());
        verify(playerTournamentService, times(1)).getGlobalRanking();
    }

    @Test
    void testGetTournamentRanking() {
        Long tournamentId = 1L;
        List<PlayerRankingDTO> expectedResponse = Arrays.asList(new PlayerRankingDTO(), new PlayerRankingDTO());
        when(playerTournamentService.getTournamentRanking(tournamentId)).thenReturn(expectedResponse);

        ResponseEntity<List<PlayerRankingDTO>> response = rankingController.getTournamentRanking(tournamentId);

        assertEquals(expectedResponse, response.getBody());
        verify(playerTournamentService, times(1)).getTournamentRanking(tournamentId);
    }
}
