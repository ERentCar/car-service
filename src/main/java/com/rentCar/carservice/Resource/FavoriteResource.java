package com.rentCar.carservice.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteResource {
    private Long id;
    private CarResource car;
    private Long clientId;
}
