package com.rentCar.carservice.Controllers;

import com.rentCar.carservice.Mapping.CarMapper;
import com.rentCar.carservice.Resource.CarResource;
import com.rentCar.carservice.Resource.CreateCarResource;
import com.rentCar.carservice.Services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper mapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.mapper = carMapper;
    }
    @GetMapping
    public List<CarResource> getAllCars(){
        return mapper.listToResource(carService.getAll());
    }
    /*@GetMapping("notRents")
    public Page<CarResource> getAllCarsNotRent(Pageable pageable){
        return mapper.modelListToPage(carService.getCarsNotRent(),pageable);
    }*/

    @GetMapping("{carId}")
    public CarResource getCarById(@PathVariable Long carId){
        return  mapper.toResource(carService.getById(carId));
    }

    @PostMapping("brand/{brandId}")
    public CarResource createCar(@PathVariable("brandId")Long brandId,
                                 @Valid @RequestBody CreateCarResource request){
        return mapper.toResource(carService.create(brandId,mapper.toModel(request)));
    }
    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        return carService.delete(carId);
    }
}
