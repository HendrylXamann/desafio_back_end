package com.challengebackend.adapters.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.application.challenges.ChallengesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengesController implements ChallengesAPI{
    private final ChallengesService challengesService;

    @Override
    public ResponseEntity<ChallengesDTO> executeChallenge(Long id, ChallengesForm form) {
        return ResponseEntity.ok(challengesService.challengeExecution(id, form));
    }

//    @Override
//    public ResponseEntity<List<ChallengesDTO>> listChallenges() {
//        return ResponseEntity.ok(challengesService.findAll());
//    }
}
