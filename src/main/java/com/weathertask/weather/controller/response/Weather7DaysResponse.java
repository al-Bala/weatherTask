package com.weathertask.weather.controller.response;

import java.util.List;

public record Weather7DaysResponse(
        List<DayInfo> weatherWeek
) {
}
