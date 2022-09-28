package com.rentCar.carservice.Mapping;

import com.rentCar.carservice.Entities.Brand;
import com.rentCar.carservice.Resource.BrandResource;
import com.rentCar.carservice.Resource.CreateBrandResource;
import com.rentCar.carservice.Shared.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BrandMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public BrandResource toResource (Brand model){return mapper.map(model,BrandResource.class);}
    public Page<BrandResource> modelListToPage(List<Brand> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,BrandResource.class),pageable,modelList.size());
    }
    public Brand toModel(CreateBrandResource resource){return mapper.map(resource,Brand.class);}

    public List<BrandResource>listToResource(List<Brand> modelList){
        return mapper.mapList(modelList,BrandResource.class);
    }
}
