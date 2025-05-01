package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {
    private final MessageError messageError;

    public IllegalArgumentException(MessageError messageError) {
        this.messageError = messageError;
    }
}
