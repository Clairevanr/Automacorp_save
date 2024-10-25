package com.emse.spring.automacorp.dto;

import java.util.List;

public record Room(Long id, String name, Integer floor, com.emse.spring.automacorp.model.SensorEntity currentTemperature, Double targetTemperature, List<Window> windows, List<Heater> heaters, Long building_id) {
}
