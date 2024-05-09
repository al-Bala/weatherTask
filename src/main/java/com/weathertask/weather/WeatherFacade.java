package com.weathertask.weather;

import com.weathertask.weather.controller.response.Weather7DaysResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WeatherFacade {
    private final WeatherChecker weatherChecker;

    public ResponseEntity<Weather7DaysResponse> checkWeather(double latitude, double longitude){
        return weatherChecker.checkWeather(latitude, longitude);
    }
}
