package com.emse.spring.automacorp.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_ROOM")
public class RoomEntity {
    @Id // (3) ID généré automatiquement
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Integer floor;
    @Column(nullable = false)
    private String name;
    private Double targetTemperature;
    @ManyToOne
    private SensorEntity currentTemperature;
    @OneToMany
    private List<WindowEntity> windows = new ArrayList<WindowEntity>() ;
    @OneToMany
    private List<HeaterEntity> heaters = new ArrayList<HeaterEntity>() ;
    @OneToOne
    private BuildingEntity building;

    public RoomEntity(){

    }
    //Default Constructor

    public RoomEntity(Long id, Integer floor, String name){
        this.id = id;
        this.floor = floor;
        this.name = name;
    }

    public RoomEntity(String name, SensorEntity temp, int i) {
    }

    public RoomEntity(String name, SensorEntity currentTemperature, Double targetTemperature) {
        this.name=name;
        this.currentTemperature=currentTemperature;
        this.targetTemperature=targetTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;
    }

    public void setBuilding(BuildingEntity building) {
    }

    public void setHeaters(List<HeaterEntity> heaters) {
        this.heaters = heaters;
    }

    public List<HeaterEntity> getHeaters(){
        return this.heaters;
    }

    public BuildingEntity getBuilding() {
        return this.building;
    }
}