package com.challengebackend.controller;
import com.challengebackend.adapters.challenges.ChallengesController;
import com.challengebackend.application.challenge.ChallengesService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ChallengeControllerTest {

    @Mock
    private ChallengesService challengesService;

    @InjectMocks
    private ChallengesController challengesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}
