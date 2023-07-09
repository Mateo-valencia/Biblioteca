package com.ceiba.biblioteca.infrastructure.adapters.input.rest.error;

import com.ceiba.biblioteca.domain.exception.PrestamoException;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PrestamoException.class)
    public final ResponseEntity<ErrorDTO> handlePrestamoExceptions(PrestamoException exception){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMensaje(exception.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
