package com.emse.spring.automacorp.model;
import java.util.ArrayList;

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
    public double Target_temperature;
    @Transient
    public SensorEntity Current_temperature;
    @OneToMany
    public ArrayList<WindowEntity> windows = new ArrayList() ;

    public RoomEntity(){

    }
    //Default Constructor

    public RoomEntity(Long id, Integer floor, String name){
        this.id = id;
        this.floor = floor;
        this.name = name;
    }

}