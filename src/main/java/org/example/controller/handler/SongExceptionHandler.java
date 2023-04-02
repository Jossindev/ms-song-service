package org.example.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class SongExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongNotFoundException.class)
    public String handleSongNotFound(Exception ex) {
        log.warn(ex.getMessage());
        return ex.getMessage();
    }
}
