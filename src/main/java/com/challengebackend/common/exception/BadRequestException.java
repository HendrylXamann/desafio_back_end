package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final MessageError messageError;

    public BadRequestException(MessageError messageError) {
        this.messageError = messageError;
    }
}
