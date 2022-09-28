package com.rentCar.carservice.Repositories;

import com.rentCar.carservice.Entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {

}
