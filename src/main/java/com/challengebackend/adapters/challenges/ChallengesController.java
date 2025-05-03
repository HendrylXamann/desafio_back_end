package com.challengebackend.adapters.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.application.challenge.ChallengesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengesController implements ChallengesAPI{
    private final ChallengesService challengesService;

    @Override
    public ResponseEntity<ChallengesDTO> executeChallenge(ChallengesForm form) {
        return ResponseEntity.ok(challengesService.challengeExecution(form));
    }
}
