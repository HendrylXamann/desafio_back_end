package com.challengebackend.service;

import com.challengebackend.infrastructure.persistence.challenges.ChallengesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ChallengesServiceImplTest {

    @Mock
    private ChallengesRepository challengesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
