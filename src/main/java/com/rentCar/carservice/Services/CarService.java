package com.rentCar.carservice.Services;

import com.rentCar.carservice.Entities.Car;
import com.rentCar.carservice.Resource.CarNoRentsResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    List<Car> getAll();
    List<Car> getAllForRents();
    /*Page<Car> getAll(Pageable pageable);*/
    Car getById(Long carId);
    Car create(Long ownerId,Long brandId,Car car);
    ResponseEntity<?> delete(Long carId);
    /*List<Car> getCarsNotRent();*/
    Car setState(Long carId,int state);
    List<CarNoRentsResource>getCarsNotRent(Long clientId);
    List<Car>getCarsByOwner(Long ownerId);
    Car setRating(Long carId,Double rating);
}
