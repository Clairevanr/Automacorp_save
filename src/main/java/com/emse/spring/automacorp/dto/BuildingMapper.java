package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.*;

import java.util.ArrayList;
import java.util.List;

public class BuildingMapper {
    public static Building of(BuildingEntity building){

        //define the Sensor outsideTemperature from RoomEntity room data
        SensorEntity outsideTemperature = new SensorEntity(
                building.getOutside_Temperature().getId(),
                building.getOutside_Temperature().getName(),
                building.getOutside_Temperature().getValue(),
                building.getOutside_Temperature().getSensorType()
        );

        //define the List<Room> rooms from RoomEntity room data
        List<Room> rooms = new ArrayList<>();
        for (RoomEntity roomEntity : building.getRooms()) {

            SensorEntity currentTemperature = new SensorEntity(
                    roomEntity.getCurrentTemperature().getId(),
                    roomEntity.getCurrentTemperature().getName(),
                    roomEntity.getCurrentTemperature().getValue(),
                    roomEntity.getCurrentTemperature().getSensorType());

            List<Window> windows = new ArrayList<>();
            for (WindowEntity windowEntity : roomEntity.getWindows()) {
                SensorEntity windowStatus = new SensorEntity(
                        windowEntity.getWindowStatus().getId(),
                        windowEntity.getWindowStatus().getName(),
                        windowEntity.getWindowStatus().getValue(),
                        windowEntity.getWindowStatus().getSensorType());
                Window window = new Window(windowEntity.getId(), windowEntity.getName(), windowStatus, windowEntity.getRoom().getId());
                windows.add(window);
            }

            List<Heater> heaters = new ArrayList<>();
            for (HeaterEntity heaterEntity : roomEntity.getHeaters()) {
                SensorEntity heaterStatus = new SensorEntity(
                        heaterEntity.getStatus().getId(),
                        heaterEntity.getStatus().getName(),
                        heaterEntity.getStatus().getValue(),
                        heaterEntity.getStatus().getSensorType());
                Heater heater = new Heater(heaterEntity.getId(), heaterEntity.getName(),heaterStatus, heaterEntity.getRoom().getId());
                heaters.add(heater);
            }

            Room room = new Room(roomEntity.getId(), roomEntity.getName(), roomEntity.getFloor(), currentTemperature, roomEntity.getTargetTemperature(), windows, heaters, roomEntity.getBuilding().getId());
            rooms.add(room);
        }

        return new Building(
                building.getId(),
                building.getName(),
                outsideTemperature,
                rooms
        );
    }
}
