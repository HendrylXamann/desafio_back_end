package com.challengebackend.service;

import com.challengebackend.infrastructure.persistence.tournaments.TournamentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TournamentsServiceImplTest {

    @Mock
    private TournamentsRepository tournamentsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}