package com.watersolution.inventory.component.exception;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected <T extends ResponseDefault, E extends CustomException> ResponseEntity<T> handleCustomException(E ex) {
        printException(ex);
        log.info("Executing CustomException.handleException");
        printFailedResponse(new ResponseDefault());
        return ResponseCreator.failedResponse((T) new ResponseDefault(), ex);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        printException(ex);
        log.info("Executing MaxUploadSizeExceededException.handleException");
        ResponseDefault responseDefault = new ResponseDefault();
        responseDefault.setResponseCode("05");
        responseDefault.setDescription("Maximum upload size exceeded");
        responseDefault.setResponseValues(Collections.singletonList("Maximum upload size exceeded"));
        printFailedResponse(responseDefault);
        return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected <T extends ResponseDefault> ResponseEntity<T> handleException(Exception ex) {
        printException(ex);
        log.info("Executing Exception.handleException");
        printFailedResponse(new ResponseDefault());
        return ResponseCreator.failedResponse((T) new ResponseDefault(), ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        printException(ex);
        log.info("Executing MethodArgumentNotValidException.handleException");
        ResponseDefault responseDefault = new ResponseDefault();
        responseDefault.setResponseCode("05");
        responseDefault.setDescription("Invalid Request");
        responseDefault.setResponseValues(errorList);
        printFailedResponse(responseDefault);
        return new ResponseEntity<>(responseDefault, HttpStatus.BAD_REQUEST);
    }

    private void printFailedResponse(ResponseDefault responseDefault) {
        log.info("Failed Response returned");
        log.debug("Failed Response returned with data : {}", responseDefault.toString());
    }

    private <E> void printException(E ex) {
        log.info("Executing RestExceptionHandler.handleException");
        log.debug("Executing RestExceptionHandler.handleException for : {}", ex.getClass().getSimpleName());
        log.error("{} : ", ex.getClass().getSimpleName(), ex);
        log.info("Creating Failed Response for : {}", ex.getClass().getSimpleName());
    }
}
