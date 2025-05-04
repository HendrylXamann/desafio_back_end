package com.challengebackend.adapters.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "3. Challenges", description = "Endpoints for executing logic challenges")
@RequestMapping("${spring.challenge.base.path}/${spring.challenge.base.version}/challenges")
public interface ChallengesAPI {

    @Operation(summary = "Execute a logic challenge", description = "Executes a specific logic challenge and records the score")
    @PostMapping("/execute")
    ResponseEntity<ChallengesDTO> executeChallenge(@RequestBody ChallengesForm form);
}
