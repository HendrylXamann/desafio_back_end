package com.challengebackend.adapters.challenges.payload;

import com.challengebackend.common.valueobjects.ChallengeTypes;
import lombok.Data;

@Data
public class ChallengesForm {
    private ChallengeTypes challengeType;
    private Integer number;
    private String inputString;
    private int[] intNumbers;
    private Integer weight;
}
