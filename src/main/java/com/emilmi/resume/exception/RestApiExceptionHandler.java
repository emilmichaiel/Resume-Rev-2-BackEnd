package com.emilmi.resume.exception;

import com.mongodb.MongoWriteException;
import jakarta.annotation.Nonnull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.List;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestApiException.class})
    public ResponseEntity<ErrorResponse> handleApiExceptions(
            RestApiException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                List.of(ex.getMessage()),
                request.getDescription(false),
                LocalDate.now(),
                ex.getStatusCode().value()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList(),
                request.getDescription(false),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({MongoWriteException.class})
    public ResponseEntity<ErrorResponse> handleApiExceptions(
            MongoWriteException ex,
            WebRequest request
    ) {
        String errorMessage = ex.getError().getMessage().substring(7, ex.getError().getMessage().indexOf("collection: "))
                + ex.getError().getMessage().substring(ex.getError().getMessage().indexOf("index: ") + 7, ex.getError().getMessage().indexOf("key:") - 4)
                + ex.getError().getMessage().substring(ex.getError().getMessage().indexOf("\""), ex.getError().getMessage().indexOf("}"))
                .replaceAll("\"", "")
                .trim();

        ErrorResponse errorResponse = new ErrorResponse(
                List.of(errorMessage),
                request.getDescription(false),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
