package com.challengebackend.application.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;

public interface ChallengesService {

    ChallengesDTO challengeExecution(Long id, ChallengesForm form);
}
