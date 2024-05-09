package com.weathertask.weather;

import com.weathertask.forecastapi.ForecastProxy;
import com.weathertask.forecastapi.response.Daily;
import com.weathertask.forecastapi.response.ForecastResponse;
import com.weathertask.weather.controller.response.DayInfo;
import com.weathertask.weather.controller.response.Weather7DaysResponse;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Service
class WeatherChecker {
    private final ForecastProxy forecastProxy;
    private final SunlightEnergyCounter energyCounter;

    public ResponseEntity<Weather7DaysResponse> checkWeather(double latitude, double longitude){
        ForecastResponse forecast = connectWithForecastApi(latitude,longitude);
        if(forecast == null){
            return ResponseEntity.notFound().build();
        }
        List<Double> sunshineDurations = forecast.daily().sunshine_duration();
        List<Double> generatedEnergies = energyCounter.countPhotovoltaicEnergy(sunshineDurations);

        Weather7DaysResponse infoFor7Days = getWeatherAndEnergyInfoFor7Days(forecast, generatedEnergies);
        return ResponseEntity.ok(infoFor7Days);
    }

    private ForecastResponse connectWithForecastApi(double latitude, double longitude) {
        String[] str = {"weather_code", "temperature_2m_max", "temperature_2m_min", "sunshine_duration"};
        try{
            return forecastProxy.getForecastFor7Days(latitude, longitude, str);
        } catch (FeignException.FeignClientException exception){
            log.error("Client exception: " + exception.status());
        } catch (FeignException.FeignServerException exception){
            log.error("Server exception: " + exception.status());
        } catch (FeignException exception){
            log.error("Feign exception: " + exception.getMessage() + " " + exception.status());
        }
        return null;
    }

    private Weather7DaysResponse getWeatherAndEnergyInfoFor7Days(ForecastResponse forecast, List<Double> generatedEnergy) {
        Daily daily = forecast.daily();
        List<DayInfo> weatherInfo7Days = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            DayInfo dayInfo = new DayInfo(
                    daily.time().get(i),
                    daily.weather_code().get(i),
                    daily.temperature_2m_max().get(i),
                    daily.temperature_2m_min().get(i),
                    generatedEnergy.get(i));
            weatherInfo7Days.add(dayInfo);
        }
        return new Weather7DaysResponse(weatherInfo7Days);
    }
}
