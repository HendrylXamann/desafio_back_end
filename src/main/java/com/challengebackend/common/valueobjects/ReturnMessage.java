package com.challengebackend.common.valueobjects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = ReturnMessageSeriallizer.class)
public enum ReturnMessage {
    TOURNAMENT_FINISHED(200, "TOURNAMENT_FINISHED", "Tournament finished successfully"),
    PLAYER_ADDED_SUCCESSFULLY(200, "PLAYER_ADDED_SUCCESSFULLY", "Player added to tournament successfully"),
    PLAYER_REMOVED_SUCCESSFULLY(200, "PLAYER_REMOVED_SUCCESSFULLY", "Player removed from tournament successfully"),
    PLAYER_DELETED_SUCCESSFULLY(200, "PLAYER_DELETED_SUCCESSFULLY", "Player deleted successfully"),
    CHALLENGE_DELETED_SUCCESSFULLY(200, "CHALLENGE_DELETED_SUCCESSFULLY", "Challenge deleted successfully"),
    TOURNAMENT_DELETED_SUCCESSFULLY(200, "TOURNAMENT_DELETED_SUCCESSFULLY", "Tournament deleted successfully");

    private final int status;
    private final String code;
    private final String message;

    ReturnMessage(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
