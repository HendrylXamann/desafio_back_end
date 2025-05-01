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
    CHALLENGE_NOT_FOUND(404, "CHALLENGE_NOT_FOUND", "Challenge not found"),
    CHALLENGE_ALREADY_EXISTS(409, "CHALLENGE_ALREADY_EXISTS", "Challenge already exists"),
    TOURNAMENT_NOT_FOUND(404, "TOURNAMENT_NOT_FOUND", "Tournament not found"),
    TOURNAMENT_ALREADY_EXISTS(409, "TOURNAMENT_ALREADY_EXISTS", "Tournament already exists"),
    BAD_REQUEST(400, "BAD_REQUEST", "Bad Request"),
    PLAYER_DELETED_SUCCESSFULLY(200, "PLAYER_DELETED_SUCCESSFULLY", "Player deleted successfully"),
    CHALLENGE_DELETED_SUCCESSFULLY(200, "CHALLENGE_DELETED_SUCCESSFULLY", "Challenge deleted successfully"),
    TOURNAMENT_DELETED_SUCCESSFULLY(200, "TOURNAMENT_DELETED_SUCCESSFULLY", "Tournament deleted successfully");

    private final int status;
    private final String code;
    private final String message;

    MessageError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
