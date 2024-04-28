package com.example.issatc.Infrastructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized: User is not authenticated");
    }

    public UnauthorizedException(String message) {
        super("Unauthorized: User is not authenticated trying to retrieve "+message +" data");
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
