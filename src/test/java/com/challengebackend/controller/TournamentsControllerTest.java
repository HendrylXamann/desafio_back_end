package com.challengebackend.controller;

import com.challengebackend.adapters.tournaments.TournamentsController;
import com.challengebackend.application.tournament.TournamentsService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TournamentsControllerTest {

    @Mock
    private TournamentsService tournamentsService;

    @InjectMocks
    private TournamentsController tournamentsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
