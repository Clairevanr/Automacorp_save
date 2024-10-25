package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {
    public static Room of(RoomEntity room) {

        //define the Sensor currentTemperature from RoomEntity room data
        SensorEntity currentTemperature = new SensorEntity(
                room.getCurrentTemperature().getId(),
                room.getCurrentTemperature().getName(),
                room.getCurrentTemperature().getValue(),
                room.getCurrentTemperature().getSensorType()
        );

        //define the List<Window> windows from RoomEntity room data
        List<Window> windows = new ArrayList<>();
        for (WindowEntity windowEntity : room.getWindows()) {
            SensorEntity windowStatus = new SensorEntity(
                    windowEntity.getWindowStatus().getId(),
                    windowEntity.getWindowStatus().getName(),
                    windowEntity.getWindowStatus().getValue(),
                    windowEntity.getWindowStatus().getSensorType());
            Window window = new Window(windowEntity.getId(), windowEntity.getName(), windowStatus, windowEntity.getRoom().getId());
            windows.add(window);
        }

        //define the List<Heater> heaters from RoomEntity room data
        List<Heater> heaters = new ArrayList<>();
        for (HeaterEntity heaterEntity : room.getHeaters()) {
            SensorEntity heaterStatus = new SensorEntity(
                    heaterEntity.getStatus().getId(),
                    heaterEntity.getStatus().getName(),
                    heaterEntity.getStatus().getValue(),
                    heaterEntity.getStatus().getSensorType());
            Heater heater = new Heater(heaterEntity.getId(), heaterEntity.getName(),heaterStatus, heaterEntity.getRoom().getId());
            heaters.add(heater);
        }

        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                currentTemperature,
                room.getTargetTemperature(),
                windows,
                heaters,
                room.getBuilding().getId()
        );
    }

}

