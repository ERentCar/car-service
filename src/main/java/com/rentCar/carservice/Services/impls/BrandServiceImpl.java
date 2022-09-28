package com.rentCar.carservice.Services.impls;

import com.rentCar.carservice.Entities.Brand;
import com.rentCar.carservice.Repositories.BrandRepository;
import com.rentCar.carservice.Services.BrandService;
import com.rentCar.carservice.Shared.Exception.ResourceNotFoundException;
import com.rentCar.carservice.Shared.Exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final Validator validator;

    public BrandServiceImpl(BrandRepository brandRepository, Validator validator) {
        this.brandRepository = brandRepository;
        this.validator = validator;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(()->new ResourceNotFoundException("BRAND",brandId));
    }

    @Override
    public Brand create(Brand brand) {
        Set<ConstraintViolation<Brand>> violations=validator.validate(brand);
        if(!violations.isEmpty()){
            throw new ResourceValidationException("BRAND",violations);
        }
        return brandRepository.save(brand);
    }
}
