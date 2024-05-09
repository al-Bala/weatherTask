package com.weathertask.weather;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class SunlightEnergyCounter {

    private final static double PHOTOVOLTAIC_POWER = 2.5;
    private final static double PANELS_EFFICIENCY = 0.2;

    public List<Double> countPhotovoltaicEnergy(List<Double> sunshineDurations){
        List<Double> energies = new ArrayList<>();
        for(double sunDuration: sunshineDurations){
            double energy = PHOTOVOLTAIC_POWER * sunDuration * PANELS_EFFICIENCY;
            double formattedEnergy = shortenValue(energy);
            energies.add(formattedEnergy);
        }
        return energies;
    }

    private static double shortenValue(double value) {
        int decimalPoint = 3;
        value *= Math.pow(10, decimalPoint);
        value = Math.floor(value);
        value /= Math.pow(10, decimalPoint);
        return value;
    }
}
