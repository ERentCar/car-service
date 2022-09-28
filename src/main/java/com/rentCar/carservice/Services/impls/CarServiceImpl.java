package com.rentCar.carservice.Services.impls;

import com.rentCar.carservice.Entities.Brand;
import com.rentCar.carservice.Entities.Car;
import com.rentCar.carservice.Repositories.BrandRepository;
import com.rentCar.carservice.Repositories.CarRepository;
import com.rentCar.carservice.Services.CarService;
import com.rentCar.carservice.Shared.Exception.ResourceNotFoundException;
import com.rentCar.carservice.Shared.Exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final Validator validator;

    public CarServiceImpl(CarRepository carRepository, BrandRepository brandRepository, Validator validator) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.validator = validator;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("CAR",carId));
    }

    @Override
    public Car create(Long brandId,Car car) {
        Set<ConstraintViolation<Car>> violations=validator.validate(car);
        if(!violations.isEmpty()){
            throw new ResourceValidationException("CAR",violations);
        }
        Brand brand=brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", brandId));

        car.setBrand(brand);
        car.setRating(0.0);
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> delete(Long carId) {
        return carRepository.findById(carId).map(car -> {
            carRepository.delete(car);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CAR", carId));
    }

    @Override
    public Car setState(Long carId) {
        Car car=carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("CAR",carId));
        carRepository.save(car.withState(1));
        car.setState(1);
        return car;
    }
}
