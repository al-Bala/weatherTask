package com.weathertask.weather.controller;

import com.weathertask.weather.WeatherFacade;
import com.weathertask.weather.controller.response.DayInfo;
import com.weathertask.weather.controller.response.Weather7DaysResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class WeatherController {
    private final WeatherFacade weatherFacade;

    //example: http://localhost:8080/weather?latitude=50.06&longitude=19.94
    @GetMapping("/weather")
    public String weather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            Model model){

        ResponseEntity<Weather7DaysResponse> endpointResponseResponseEntity = weatherFacade.checkWeather(latitude, longitude);
        List<DayInfo> dayInfos = endpointResponseResponseEntity.getBody().weatherWeek();

        model.addAttribute("weatherInfo", dayInfos);
        model.addAttribute("icons", getIcons());
        return "index";
    }

    private Map<Integer, String> getIcons(){
        Map<Integer, String> weatherIcons = new HashMap<>();
        weatherIcons.put(0, "fa-solid fa-sun");
        weatherIcons.put(1, "fa-solid fa-cloud-sun");
        weatherIcons.put(2, "fa-solid fa-cloud-sun");
        weatherIcons.put(3, "fa-solid fa-cloud");
        weatherIcons.put(45, "fa-solid fa-cloud");
        weatherIcons.put(48, "fa-solid fa-cloud");
        weatherIcons.put(51, "fa-solid fa-cloud-rain");
        weatherIcons.put(53, "fa-solid fa-cloud-rain");
        weatherIcons.put(55, "fa-solid fa-cloud-rain");
        weatherIcons.put(61, "fa-solid fa-cloud-rain");
        weatherIcons.put(63, "fa-solid fa-cloud-rain");
        weatherIcons.put(66, "fa-solid fa-cloud-rain");
        weatherIcons.put(67, "fa-solid fa-cloud-rain");
        weatherIcons.put(71, "fa-solid fa-cloud-rain");
        weatherIcons.put(73, "fa-solid fa-cloud-rain");
        weatherIcons.put(75, "fa-solid fa-cloud-rain");
        weatherIcons.put(77, "fa-solid fa-cloud-rain");
        weatherIcons.put(85, "fa-solid fa-cloud-rain");
        weatherIcons.put(86, "fa-solid fa-cloud-rain");
        weatherIcons.put(56, "fa-solid fa-snowflake");
        weatherIcons.put(57, "fa-solid fa-snowflake");
        weatherIcons.put(65, "fa-solid fa-cloud-showers-heavy");
        weatherIcons.put(80, "fa-solid fa-cloud-showers-heavy");
        weatherIcons.put(81, "fa-solid fa-cloud-showers-heavy");
        weatherIcons.put(82, "fa-solid fa-cloud-showers-heavy");
        weatherIcons.put(95, "fa-solid fa-cloud-bolt");
        weatherIcons.put(96, "fa-solid fa-cloud-bolt");
        weatherIcons.put(99, "fa-solid fa-cloud-bolt");
        return weatherIcons;
    }
}
