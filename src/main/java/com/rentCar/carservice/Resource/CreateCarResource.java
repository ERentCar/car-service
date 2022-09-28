package com.rentCar.carservice.Resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateCarResource {
    @NotNull
    @Size(max = 100)
    private String model;

    @NotNull
    @Size(max = 12)
    private String plateNumber;

    @NotNull
    private Double dayCost;

    private Double mileage;

    @NotNull
    private String image;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    private String description;

    private Integer state;

    private Long ownerId;
}
