package fr.volkan.back.handler;

import fr.volkan.back.product.exception.ProductAlreadyExistsException;
import fr.volkan.back.product.exception.ProductNotFoundException;
import fr.volkan.back.user.exception.ReservedEmailException;
import fr.volkan.back.user.exception.UserAlreadyExistsException;
import fr.volkan.back.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ProblemDetail handleProductAlreadyExists() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Product already exists");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Product not found");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleUserAlreadyExists() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "User already exists");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "User not found");
    }

    @ExceptionHandler(ReservedEmailException.class)
    public ProblemDetail handleReservedEmail() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "This email address is reserved");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }
}
