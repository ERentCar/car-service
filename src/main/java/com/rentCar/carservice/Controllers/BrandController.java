package com.rentCar.carservice.Controllers;

import com.rentCar.carservice.Mapping.BrandMapper;
import com.rentCar.carservice.Resource.BrandResource;
import com.rentCar.carservice.Resource.CreateBrandResource;
import com.rentCar.carservice.Services.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper mapper;

    public BrandController(BrandService brandService, BrandMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }
    @GetMapping
    public List<BrandResource> getAllBrands(){
        return mapper.listToResource(brandService.getAll());
    }

    @GetMapping("{brandId}")
    public BrandResource getBrandById(@PathVariable Long brandId){
        return  mapper.toResource(brandService.getById(brandId));
    }

    @PostMapping
    public BrandResource createBrand(@Valid @RequestBody CreateBrandResource request){
        return mapper.toResource(brandService.create(mapper.toModel(request)));
    }
}
