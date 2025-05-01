package com.challengebackend.common.exception;

import com.challengebackend.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
  private final MessageError messageError;

  public ResourceNotFoundException(MessageError messageError) {
    this.messageError = messageError;
  }
}
