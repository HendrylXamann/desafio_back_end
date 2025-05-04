package com.challengebackend.service;

import com.challengebackend.adapters.challenges.payload.ChallengesDTO;
import com.challengebackend.adapters.challenges.payload.ChallengesForm;
import com.challengebackend.application.challenge.ChallengesServiceImpl;
import com.challengebackend.application.tournament.playertournment.PlayerTournamentService;
import com.challengebackend.common.exception.IllegalArgumentException;
import com.challengebackend.common.messageerror.MessageError;
import com.challengebackend.common.valueobjects.ChallengeTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChallengeServiceImplTest {

    @Mock
    private PlayerTournamentService playerTournamentService;

    @InjectMocks
    private ChallengesServiceImpl challengesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChallengeExecutionFibonacci() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.FIBONACCI);
        form.setNumber(5);
        form.setPlayerTournamentId(1L);
        ChallengesDTO result = challengesService.challengeExecution(form);

        assertEquals(5, result.getFibonacciResult());
        verify(playerTournamentService, times(1)).registerScore(1L, ChallengeTypes.FIBONACCI, 10);
    }

    @Test
    void testChallengeExecutionPalindrome() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.PALINDROME);
        form.setInputString("racecar");
        form.setPlayerTournamentId(1L);

        ChallengesDTO result = challengesService.challengeExecution(form);

        assertEquals("true", result.getPalindromeResultAsString());
        verify(playerTournamentService, times(1))
                .registerScore(1L, ChallengeTypes.PALINDROME, 5);
    }



    @Test
    void testChallengeExecutionCustomSort() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.CUSTOM_SORT);
        form.setIntNumbers(new int[]{3, 1, 2});
        form.setPlayerTournamentId(1L);
        ChallengesDTO result = challengesService.challengeExecution(form);

        assertArrayEquals(new int[]{1, 2, 3}, (int[]) result.getIntNumbersSortedResult());
        verify(playerTournamentService, times(1)).registerScore(1L, ChallengeTypes.CUSTOM_SORT, 7);
    }


    @Test
    void testFibonacciInvalidNumber() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.FIBONACCI);
        form.setNumber(-1);
        form.setPlayerTournamentId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> challengesService.challengeExecution(form));
        assertEquals(MessageError.NUMBER_MUST_BE_NON_NEGATIVE_INTEGER, exception.getMessageError());
    }

    @Test
    void testPalindromeNullInput() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.PALINDROME);
        form.setInputString(null);
        form.setPlayerTournamentId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> challengesService.challengeExecution(form));
        assertEquals(MessageError.PALINDROME_INPUTSTRING_NOT_FOUND, exception.getMessageError());
    }

    @Test
    void testCustomSortEmptyArray() {
        ChallengesForm form = new ChallengesForm();
        form.setChallengeType(ChallengeTypes.CUSTOM_SORT);
        form.setIntNumbers(new int[]{});
        form.setPlayerTournamentId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> challengesService.challengeExecution(form));
        assertEquals(MessageError.CUSTOM_SORT_ARRAY_NOT_FOUND, exception.getMessageError());
    }
}
