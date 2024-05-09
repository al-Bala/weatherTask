package com.weathertask.weather.controller.response;

public record DayInfo(
        String date,
        int weatherCode,
        double maxTemperature,
        double minTemperature,
        double generatedEnergyKWh
) {
}
