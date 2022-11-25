package com.rentCar.carservice.Mapping;

import com.rentCar.carservice.Entities.Car;
import com.rentCar.carservice.Resource.CarNoRentsResource;
import com.rentCar.carservice.Resource.CarRentResource;
import com.rentCar.carservice.Resource.CarResource;
import com.rentCar.carservice.Resource.CreateCarResource;
import com.rentCar.carservice.Shared.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CarMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CarResource toResource(Car model){return mapper.map(model,CarResource.class);}
    public CarRentResource toResourceRent(Car model){return mapper.map(model,CarRentResource.class);}
    public Page<CarResource> modelListToPage(List<Car> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,CarResource.class),pageable,modelList.size());
    }
    public Car toModel (CreateCarResource resource){return mapper.map(resource,Car.class);}
    public List<CarResource> listToResource(List<Car> modelList){
        return mapper.mapList(modelList,CarResource.class);
    }
    public List<CarRentResource> listToResourceForRents(List<Car> modelList){
        return mapper.mapList(modelList,CarRentResource.class);
    }
    //For Favorites
    public List<CarRentResource> listToRentResource(List<Car> modelList){
        return mapper.mapList(modelList,CarRentResource.class);
    }
    public Page<CarNoRentsResource> noRentListToPage(List<CarNoRentsResource> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,CarNoRentsResource.class),pageable,modelList.size());
    }
}
