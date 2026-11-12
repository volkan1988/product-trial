package fr.volkan.back.handler;

import fr.volkan.back.product.exception.ProductAlreadyExistsException;
import fr.volkan.back.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
}
