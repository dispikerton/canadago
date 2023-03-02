package ru.ivanov.canadago.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String articleNotFound) {
    super(articleNotFound);
  }
}
