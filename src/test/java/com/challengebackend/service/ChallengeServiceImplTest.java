package com.challengebackend.service;

import com.challengebackend.infrastructure.persistence.challenge.ChallengeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ChallengeServiceImplTest {

    @Mock
    private ChallengeRepository challengeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
