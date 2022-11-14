package com.rentCar.carservice.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRentResource {
    private Long id;

    private String model;

    private String plateNumber;

    private Double dayCost;

    private Double mileage;

    private String image;

    private String type;

    private String description;

    private int state;
}
