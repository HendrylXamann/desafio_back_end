package com.challengebackend.controller;
import com.challengebackend.adapters.challenges.ChallengesController;
import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.application.challenge.ChallengesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChallengeControllerTest {

    @Mock
    private ChallengesService challengesService;

    @InjectMocks
    private ChallengesController challengesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteChallenge() {
        ChallengesForm form = new ChallengesForm();
        ChallengesDTO expectedResponse = new ChallengesDTO();
        when(challengesService.challengeExecution(form)).thenReturn(expectedResponse);

        ResponseEntity<ChallengesDTO> response = challengesController.executeChallenge(form);

        assertEquals(expectedResponse, response.getBody());
        verify(challengesService, times(1)).challengeExecution(form);
    }
}
