package com.challengebackend.application.challenge;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;

public interface ChallengesService {

    ChallengesDTO challengeExecution(ChallengesForm form);
}
