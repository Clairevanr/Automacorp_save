package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.SensorEntity;

public record Heater(Long id, String name, SensorEntity Status, Long roomId) {
}
