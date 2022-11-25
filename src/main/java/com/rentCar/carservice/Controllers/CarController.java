package com.rentCar.carservice.Controllers;

import com.rentCar.carservice.Mapping.CarMapper;
import com.rentCar.carservice.Resource.CarNoRentsResource;
import com.rentCar.carservice.Resource.CarRentResource;
import com.rentCar.carservice.Resource.CarResource;
import com.rentCar.carservice.Resource.CreateCarResource;
import com.rentCar.carservice.Services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
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
    @GetMapping("ForRents")
    public List<CarRentResource> getAllCarsForRent(){
        return mapper.listToResourceForRents(carService.getAllForRents());
    }

    /*@GetMapping("notRents/{clientId}")
    public Page<CarResource> getAllCarsNotRent(Pageable pageable){
        return mapper.modelListToPage(carService.getCarsNotRent(),pageable);
    }*/
    @GetMapping("notRents/{clientId}")
    public Page<CarNoRentsResource> getAllCarsNotRent(@PathVariable("clientId")Long clientId,Pageable pageable){
        return mapper.noRentListToPage(carService.getCarsNotRent(clientId),pageable) ;
    }

    @GetMapping("owner/{ownerId}")
    public Page<CarResource> getCarsByOwner(Pageable pageable,@PathVariable Long ownerId){
        return mapper.modelListToPage(carService.getCarsByOwner(ownerId),pageable);
    }

    @GetMapping("{carId}")
    public CarResource getCarById(@PathVariable Long carId){
        return  mapper.toResource(carService.getById(carId));
    }

    @PostMapping("owner/{ownerId}/brand/{brandId}")
    public CarResource createCar(@PathVariable("ownerId")Long ownerId,
                                 @PathVariable("brandId")Long brandId,
                                 @Valid @RequestBody CreateCarResource request){
        return mapper.toResource(carService.create(ownerId,brandId,mapper.toModel(request)));
    }

    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        return carService.delete(carId);
    }

    @PutMapping("{carId}/rating/{rating}")
    public void updateRating(@PathVariable("carId")Long carId,
                                    @PathVariable("rating")Double rating){
        carService.setRating(carId,rating);
    }
    @PutMapping("setState/{carId}/state/{state}")
    public CarResource CarResource(@PathVariable("carId")Long carId,
                                   @PathVariable("state")int state){
        return mapper.toResource(carService.setState(carId,state));
    }
}
