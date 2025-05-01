package com.challengebackend.application.challenges;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.exception.ResourceNotFoundException;
import com.challengebackend.common.messageerror.MessageError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.challengebackend.infrastructure.persistence.challenges.ChallengesRepository;

@Service
@RequiredArgsConstructor
public class ChallengesServiceImpl implements ChallengesService{
    private final ChallengesRepository challengesRepository;

    @Override
    public ChallengesDTO challengeExecution(Long id, ChallengesForm form) {
        validateChallengeExists(id);

        return switch (form.getChallengeType()) {
            case FIBONACCI -> new ChallengesDTO(id, fibonacci(form));
            case PALINDROME -> new ChallengesDTO(id, isPalindrome(form));
            case CUSTOM_SORT -> new ChallengesDTO(id, customSort(form));
        };
    }

    private Integer fibonacci(ChallengesForm form) {
        if (form.getNumber() == null){
         throw new IllegalArgumentException(MessageError.FIBONACCI_NUMBER_NOT_FOUND);
        }
        return fibonacciCalc(form.getNumber());
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

    private Integer fibonacciCalc(Integer numberParam) {
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

    private int[] customSortLogic(int[] array) {
        //feio mas funciona, pesquisar alteranativas melhores dps
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    private void validateChallengeExists(Long id) {
        challengesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(MessageError.CHALLENGE_NOT_FOUND));
    }
}
