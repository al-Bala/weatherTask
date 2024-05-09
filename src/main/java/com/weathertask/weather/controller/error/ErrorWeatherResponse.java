package com.weathertask.weather.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorWeatherResponse(
        String message,
        String parameter,
        HttpStatus status
) {
}
