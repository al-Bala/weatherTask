package com.weathertask.forecastapi;

import com.weathertask.forecastapi.response.ForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client")
public interface ForecastProxy {

    @GetMapping("/v1/forecast")
    ForecastResponse getForecastFor7Days(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("daily") String[] daily);
}
