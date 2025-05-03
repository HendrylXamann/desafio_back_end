package com.challengebackend.adapters.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/challenges")
public interface ChallengesAPI {

    @PostMapping("/{id}/execute")
    ResponseEntity<ChallengesDTO> executeChallenge(@PathVariable Long challengeId, @RequestBody ChallengesForm form);
}
