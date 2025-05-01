package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
    private final MessageError messageError;

    public AuthenticationException(MessageError messageError) {
        this.messageError = messageError;
    }
}
