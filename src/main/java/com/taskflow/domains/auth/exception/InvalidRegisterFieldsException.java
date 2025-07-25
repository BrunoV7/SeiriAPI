package com.taskflow.domains.auth.exception;

public class InvalidRegisterFieldsException extends RuntimeException {

    public InvalidRegisterFieldsException(String message) { super(message); }

    public InvalidRegisterFieldsException(){ super("Invalid fields for register new user"); }

}
