package com.challengebackend.service;

import com.challengebackend.infrastructure.persistence.players.PlayersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PlayersServiceImplTest {

    @Mock
    private PlayersRepository playersRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
