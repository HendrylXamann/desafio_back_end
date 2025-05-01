package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private final MessageError messageError;

    public InternalServerErrorException(MessageError messageError) {
        this.messageError = messageError;
    }
}
