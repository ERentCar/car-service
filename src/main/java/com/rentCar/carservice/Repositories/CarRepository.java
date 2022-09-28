package com.rentCar.carservice.Repositories;

import com.rentCar.carservice.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
