package com.rentCar.carservice.Resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBrandResource {
    @NotNull
    private String name;
}
