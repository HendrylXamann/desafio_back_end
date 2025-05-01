package com.challengebackend.application.challenges;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.challengebackend.infrastructure.persistence.challenges.ChallengesRepository;

@Service
@RequiredArgsConstructor
public class ChallengesServiceImpl implements ChallengesService{
    private final ChallengesRepository challengesRepository;

}
