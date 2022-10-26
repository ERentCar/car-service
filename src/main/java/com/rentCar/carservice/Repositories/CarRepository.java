package com.rentCar.carservice.Repositories;

import com.rentCar.carservice.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value="select*from cars where state=0", nativeQuery = true)
    List<Car> findCarsNotRents();
    @Query(value="select*from cars where owner_id=?1", nativeQuery = true)
    List<Car> findCarsByOwner(Long ownerId);
}
