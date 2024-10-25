package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;

import java.util.List;

public record Building(Long id, String name, SensorEntity Outside_Temperature, List<Room> rooms) {
}
