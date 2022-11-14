package com.rentCar.carservice.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerCarResource {
    private Long id;
    private String names;
    private String lastNames;
    private String image;
    private Double rating;
}
