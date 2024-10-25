package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.SensorEntity;

public class HeaterMapper {
    public static Heater of(HeaterEntity heater) {
        SensorEntity heaterStatus = new SensorEntity(
                heater.getStatus().getId(),
                heater.getStatus().getName(),
                heater.getStatus().getValue(),
                heater.getStatus().getSensorType()
        );

        return new Heater(
                heater.getId(),
                heater.getName(),
                heaterStatus,
                heater.getRoom().getId()
        );
    }
}


