package com.challengebackend.controller;

import com.challengebackend.adapters.players.PlayersController;
import com.challengebackend.application.player.PlayersService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerControllerTest {
    @Mock
    private PlayersService playersService;

    @InjectMocks
    private PlayersController playersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
