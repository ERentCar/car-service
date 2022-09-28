package com.rentCar.carservice.Services;

import com.rentCar.carservice.Entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand getById(Long brandId);
    Brand create (Brand brand);
}
