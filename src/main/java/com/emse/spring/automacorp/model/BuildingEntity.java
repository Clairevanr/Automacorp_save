package com.emse.spring.automacorp.model;

import com.emse.spring.automacorp.dto.Room;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class BuildingEntity {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "outside_temperature_id")
    private SensorEntity Outside_Temperature;

    @OneToMany
    private List<RoomEntity> rooms = new ArrayList<RoomEntity>();

    public String getName(){
        return this.name;
    }

    public SensorEntity getOutside_Temperature() {
        return Outside_Temperature;
    }

    public void setOutside_Temperature(SensorEntity Outside_Temperature) {
        this.Outside_Temperature = Outside_Temperature;
    }

    public BuildingEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public List<RoomEntity> getRooms() {
        return this.rooms;
    }
}
