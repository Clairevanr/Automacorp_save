package com.emse.spring.automacorp.api;


public record RoomCommand(String name, Double currentTemperature, Double targetTemperature) {
}
