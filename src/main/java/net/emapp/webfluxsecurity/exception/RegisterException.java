package net.emapp.webfluxsecurity.exception;

public class RegisterException extends ApiException{
    public RegisterException(String message, String errorCode) {
        super(message, errorCode);
    }
}
