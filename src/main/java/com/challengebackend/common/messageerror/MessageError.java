package com.challengebackend.common.messageerror;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessageErrorSerializer.class)
public enum MessageError {
    ACCESS_DENIED(403, "ACCESS_DENIED", "Access Denied"),
    EXPIRED_TOKEN(401, "EXPIRED_TOKEN", "Token has expired"),
    TOKEN_NOT_FOUND(401, "TOKEN_NOT_FOUND", "Token not found"),
    MALFORMED_TOKEN(401, "MALFORMED_TOKEN", "The provided token is malformed or invalid"),
    PLAYER_NOT_FOUND(404, "PLAYER_NOT_FOUND", "Player not found"),
    PLAYER_ALREADY_EXISTS(409, "PLAYER_ALREADY_EXISTS", "Player already exists"),
    CANNOT_DELETE_PLAYER_REGISTERED_TOURNAMENT(409, "CANNOT_DELETE_PLAYER_REGISTERED_TOURNAMENT", "Cannot delete player registered in a tournament"),
    CANNOT_UPDATE_PLAYER_NAME_REGISTERED_TOURNAMENT(409, "CANNOT_UPDATE_PLAYER_REGISTERED_TOURNAMENT", "Cannot update name player registered in a tournament"),
    PLAYER_NOT_FOUND_IN_TOURNAMENT(404, "PLAYER_NOT_FOUND_IN_TOURNAMENT", "Player not found in tournament"),
    CHALLENGE_NOT_FOUND(404, "CHALLENGE_NOT_FOUND", "Challenge not found"),
    CHALLENGE_ALREADY_EXISTS(409, "CHALLENGE_ALREADY_EXISTS", "Challenge already exists"),
    TOURNAMENT_NOT_FOUND(404, "TOURNAMENT_NOT_FOUND", "Tournament not found"),
    TOURNAMENT_ALREADY_EXISTS(409, "TOURNAMENT_ALREADY_EXISTS", "Tournament already exists"),
    BAD_REQUEST(400, "BAD_REQUEST", "Bad Request"),
    PLAYER_ALREADY_REGISTERED_TOURNAMENT(409, "PLAYER_ALREADY_REGISTERED_TOURNAMENT", "Player already registered in this tournament"),
    TOURNAMENT_ALREADY_FINALIZED(409, "TOURNAMENT_ALREADY_FINALIZED", "Tournament already finalized"),
    FIBONACCI_NUMBER_NOT_FOUND(400, "FIBONACCI_NUMBER_NOT_FOUND", "Fibonacci number not found"),
    PALINDROME_INPUTSTRING_NOT_FOUND(400, "PALINDROME_INPUTSTRING_NOT_FOUND", "Palindrome input string not found"),
    CUSTOM_SORT_ARRAY_NOT_FOUND(400, "CUSTOM_SORT_ARRAY_NOT_FOUND", "Custom sort array not found"),
    NUMBER_MUST_BE_NON_NEGATIVE_INTEGER(400, "NUMBER_MUST_BE_NON_NEGATIVE_INTEGER", "Number must be a non-negative integer"),
    PLAYER_TOURNAMENT_NOT_FOUND(404, "PLAYER_TOURNAMENT_NOT_FOUND", "Player tournament not found"),
    CHALLENGE_TYPE_NOT_FOUND(400, "CHALLENGE_TYPE_NOT_FOUND", "Challenge type not found"),;

    private final int status;
    private final String code;
    private final String message;

    MessageError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
