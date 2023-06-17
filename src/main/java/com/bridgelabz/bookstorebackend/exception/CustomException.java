package com.bridgelabz.bookstorebackend.exception;

import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream()
                .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO respDTO = new ResponseDTO("Exception while processing rest request", errorMessage);
        return new ResponseEntity<>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDTO> handleAddressBookException(BookStoreException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing Rest request",
                exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}