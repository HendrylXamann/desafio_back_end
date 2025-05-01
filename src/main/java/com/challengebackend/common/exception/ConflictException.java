package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    public final MessageError messageError;

    public ConflictException(MessageError messageError) {
        this.messageError = messageError;
    }
}
