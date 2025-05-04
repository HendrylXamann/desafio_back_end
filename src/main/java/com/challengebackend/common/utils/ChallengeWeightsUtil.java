package com.challengebackend.common.utils;

import com.challengebackend.common.valueobjects.ChallengeTypes;

import java.util.Map;

public class ChallengeWeightsUtil {
    private static final Map<ChallengeTypes, Integer> challengeWeights = Map.of(
            ChallengeTypes.FIBONACCI, 10,
            ChallengeTypes.PALINDROME, 5,
            ChallengeTypes.CUSTOM_SORT, 7
    );
    public static Integer getWeight(ChallengeTypes challengeType) {
        return challengeWeights.getOrDefault(challengeType, 0); // Default to 0 if not found
    }
}
