package com.rentCar.carservice.Resource;

import com.rentCar.carservice.Entities.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarNoRentsResource {
    private CarResource carResource;
    private Boolean favorite;
    private Long favoriteId;

    public CarNoRentsResource(CarResource car, boolean b,Long favoriteId) {
        carResource=car;
        favorite=b;
        this.favoriteId=favoriteId;
    }
}
