package com.rentCar.carservice.Services.impls;

import com.rentCar.carservice.Entities.Brand;
import com.rentCar.carservice.Entities.Car;
import com.rentCar.carservice.Entities.Favorite;
import com.rentCar.carservice.Mapping.CarMapper;
import com.rentCar.carservice.Repositories.BrandRepository;
import com.rentCar.carservice.Repositories.CarRepository;
import com.rentCar.carservice.Repositories.FavoriteRepository;
import com.rentCar.carservice.Resource.CarNoRentsResource;
import com.rentCar.carservice.Resource.CarRentResource;
import com.rentCar.carservice.Resource.CarResource;
import com.rentCar.carservice.Services.CarService;
import com.rentCar.carservice.Shared.Exception.ResourceNotFoundException;
import com.rentCar.carservice.Shared.Exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final FavoriteRepository favoriteRepository;
    private final ApiCall apiCall;
    private final Validator validator;
    private final CarMapper mapper;

    public CarServiceImpl(CarRepository carRepository, BrandRepository brandRepository,
                          FavoriteRepository favoriteRepository, ApiCall apiCall, Validator validator, CarMapper mapper) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.favoriteRepository = favoriteRepository;
        this.apiCall = apiCall;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAllForRents() {
        return carRepository.findAll();
    }

    @Override
    public Car getById(Long carId) {
        Car car=carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("CAR",carId));
        car.setOwnerCarResource(apiCall.getOwner(car.getOwnerId()));
        return car;
    }
    @Override
    public List<CarNoRentsResource> getCarsNotRent(Long clientId){
        List<CarNoRentsResource>list=new ArrayList<CarNoRentsResource>();
        List<Car> carsNotRent=carRepository.findCarsNotRents();
        List<Favorite> favoriteResources=favoriteRepository.getCarsFavoritesClientId(clientId);
        List<CarResource> carRentResourceList=mapper.listToResource(carsNotRent);
        if(favoriteResources.size()>0){
            for (CarResource car:carRentResourceList){
                CarNoRentsResource aux = null;
                for(Favorite favorite:favoriteResources){
                    if(car.getId()==favorite.getCar().getId()){
                        aux=new CarNoRentsResource(car,true,favorite.getId());
                        break;
                    }
                    else{
                        aux=new CarNoRentsResource(car,false,0L);
                    }
                    
                }
                list.add(aux);
            }
            return list;
        } else{
            for (CarResource car:carRentResourceList) {
                CarNoRentsResource aux;
                aux=new CarNoRentsResource(car,false,0L);
                list.add(aux);
            }
        }
        return list;

    }

    @Override
    public List<Car> getCarsByOwner(Long ownerId) {
        return carRepository.findCarsByOwner(ownerId);
    }

    @Override
    public Car setRating(Long carId,Double rating) {

        Car car=carRepository.findById(carId).map(aux->carRepository.save(aux.withRating(rating)))
                .orElseThrow(()->new ResourceNotFoundException("CAR",carId));
        car.setRating(rating);
        return car;
    }

    @Override
    public Car create(Long ownerId,Long brandId,Car car) {
        Set<ConstraintViolation<Car>> violations=validator.validate(car);
        if(!violations.isEmpty()){
            throw new ResourceValidationException("CAR",violations);
        }
        Brand brand=brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", brandId));

        car.setBrand(brand);
        car.setRating(0.0);
        car.setOwnerId(ownerId);
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> delete(Long carId) {
        return carRepository.findById(carId).map(car -> {
            carRepository.delete(car);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CAR", carId));
    }

    @Override
    public Car setState(Long carId,int state) {
        Car car=carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("CAR",carId));
        carRepository.save(car.withState(state));
        car.setState(state);
        return car;
    }
}
