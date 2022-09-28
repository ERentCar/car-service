package com.rentCar.carservice.Services;

import com.rentCar.carservice.Entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    List<Car> getAll();
    /*Page<Car> getAll(Pageable pageable);*/
    Car getById(Long carId);
    Car create(Long brandId,Car car);
    ResponseEntity<?> delete(Long carId);
    /*List<Car> getCarsNotRent();*/
    Car setState(Long carId);
}
