package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_HEATER")
public class HeaterEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @JoinColumn(nullable = false)
    @ManyToOne
    private RoomEntity room;
    @JoinColumn(nullable = false)
    @OneToOne
    private SensorEntity status;

    public HeaterEntity(String name, RoomEntity room, SensorEntity status) {
        this.name = name;
        this.room = room;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public SensorEntity getStatus() {
        return status;
    }

    public HeaterEntity(){ //default constructor

    }


    public void setId(Long id) {
    }


}
