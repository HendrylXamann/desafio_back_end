package com.challengebackend.adapters.challenges.payload;
import com.challengebackend.common.valueobjects.PalindromeStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChallengesDTO {
    private Long id;
    private Integer fibonacciResult = 0;
    @JsonIgnore
    private PalindromeStatus palindromeResult = PalindromeStatus.NOT_AVAILABLE;
    private int[] intNumbersSortedResult;

    public ChallengesDTO() {
    }

    public ChallengesDTO(Long id, Integer fibonacciResult) {
        this.id = id;
        this.fibonacciResult = fibonacciResult;
    }

    public ChallengesDTO(Long id, boolean palindromeResult) {
        this.id = id;
        this.palindromeResult = palindromeResult ? PalindromeStatus.TRUE : PalindromeStatus.FALSE;
    }

    public ChallengesDTO(Long id, int[] intNumbersSortedResult) {
        this.id = id;
        this.intNumbersSortedResult = intNumbersSortedResult;
    }

    @JsonProperty("palindromeResult")
    public String getPalindromeResultAsString() {
        return switch (palindromeResult) {
            case TRUE -> "true";
            case FALSE -> "false";
            case NOT_AVAILABLE -> "N/A";
        };
    }
}
