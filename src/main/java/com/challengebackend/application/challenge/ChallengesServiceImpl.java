package com.challengebackend.application.challenge;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.application.tournament.playertournment.PlayerTournamentService;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.utils.ChallengeWeightsUtil;
import com.challengebackend.common.valueobjects.ChallengeTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengesServiceImpl implements ChallengesService{
    private final PlayerTournamentService playerTournamentService;


    @Override
    public ChallengesDTO challengeExecution(ChallengesForm form) {
        Integer weight = form.getWeight() != null ? form.getWeight() : ChallengeWeightsUtil.getWeight(form.getChallengeType());

        ChallengesDTO result;
        switch (form.getChallengeType()) {
            case FIBONACCI -> result = new ChallengesDTO(fibonacci(form));
            case PALINDROME -> result = new ChallengesDTO(isPalindrome(form));
            case CUSTOM_SORT -> result = new ChallengesDTO(customSort(form));
            default -> throw new IllegalArgumentException(MessageError.CHALLENGE_TYPE_NOT_FOUND);
        }

        registerScore(form.getPlayerTournamentId(), form.getChallengeType(), weight);
        return result;
    }

    private Integer fibonacci(ChallengesForm form) {
        if (form.getNumber() == null){
         throw new IllegalArgumentException(MessageError.FIBONACCI_NUMBER_NOT_FOUND);
        }
        return calculateFibonacci(form.getNumber());
    }

    private boolean isPalindrome(ChallengesForm form) {
        if (form.getInputString() == null){
            throw new IllegalArgumentException(MessageError.PALINDROME_INPUTSTRING_NOT_FOUND);
        }
        return isPalindromeValidation(form.getInputString());
    }

    private int[] customSort(ChallengesForm form) {
        if (form.getIntNumbers() == null || form.getIntNumbers().length == 0){
            throw new IllegalArgumentException(MessageError.CUSTOM_SORT_ARRAY_NOT_FOUND);
        }
        return customSortLogic(form.getIntNumbers());
    }

    private Integer calculateFibonacci(Integer numberParam) {
        Integer position = numberParam;
        if (position == null || position < 0) {
            throw new IllegalArgumentException(MessageError.NUMBER_MUST_BE_NON_NEGATIVE_INTEGER);
        }

        if (position == 0) return 0;
        if (position == 1) return 1;

        int previous = 0;
        int current = 1;
        int next;

        for (int counter = 2; counter <= position; counter++) {
            next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    private boolean isPalindromeValidation(String input) {
        String sanitized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return sanitized.equals(new StringBuilder(sanitized).reverse().toString());
    }

    private int[] customSortLogic(int[] numbers) {
        int arrayLength = numbers.length;
        for (int outerIndex = 0; outerIndex < arrayLength - 1; outerIndex++) {
            for (int innerIndex = 0; innerIndex < arrayLength - outerIndex - 1; innerIndex++) {
                if (numbers[innerIndex] > numbers[innerIndex + 1]) {
                    int temporaryValue = numbers[innerIndex];
                    numbers[innerIndex] = numbers[innerIndex + 1];
                    numbers[innerIndex + 1] = temporaryValue;
                }
            }
        }
        return numbers;
    }

    private void registerScore(Long playerTournamentId, ChallengeTypes challengeType, Integer weight) {
        if (playerTournamentId != null) {
            playerTournamentService.registerScore(
                    playerTournamentId,
                    challengeType,
                    weight
            );
        }
    }

}
