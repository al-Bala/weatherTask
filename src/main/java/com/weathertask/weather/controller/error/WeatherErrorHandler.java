package com.weathertask.weather.controller.error;

import com.weathertask.weather.controller.WeatherController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Log4j2
@ControllerAdvice(assignableTypes = WeatherController.class)
public class WeatherErrorHandler {

//    @ExceptionHandler(SongNotFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorSongResponseDto handleException(SongNotFoundException exception){
//        log.warn("SongNotFoundException while accessing song");
//        return new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorWeatherResponse handleBindException(MissingServletRequestParameterException ex) {
        log.error("Missing parameters during fetching weather");
        return new ErrorWeatherResponse(ex.getMessage(), ex.getParameterName(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorWeatherResponse handleBindException2(MethodArgumentTypeMismatchException ex) {
        log.error("Bad data type during fetching weather");
        return new ErrorWeatherResponse(ex.getMessage(), ex.getName(), HttpStatus.BAD_REQUEST);
    }

}
