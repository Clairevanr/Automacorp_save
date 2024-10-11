package com.emse.spring.automacorp.model;
import jakarta.persistence.*;
@Entity
@Table(name = "SP_WINDOW")
public class WindowEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private SensorEntity windowStatus;
    @ManyToOne
    private RoomEntity room;


    public WindowEntity() {
    }

    public WindowEntity(String name, SensorEntity sensor, RoomEntity room) {
        this.windowStatus = sensor;
        this.name = name;
        this.room = room;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Transient
    public SensorEntity getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(SensorEntity windowStatus) {
        this.windowStatus = windowStatus;
    }
}