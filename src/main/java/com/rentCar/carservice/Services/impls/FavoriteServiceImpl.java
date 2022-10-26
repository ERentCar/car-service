package com.rentCar.carservice.Services.impls;

import com.rentCar.carservice.Entities.Car;
import com.rentCar.carservice.Entities.Favorite;
import com.rentCar.carservice.Repositories.CarRepository;
import com.rentCar.carservice.Repositories.FavoriteRepository;
import com.rentCar.carservice.Services.FavoriteService;
import com.rentCar.carservice.Shared.Exception.ResourceNotFoundException;
import com.rentCar.carservice.Shared.Exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private static final String ENTITY="Favorite";
    private final FavoriteRepository favoriteRepository;
    private final CarRepository carRepository;
    private final Validator validator;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, CarRepository carRepository, Validator validator) {
        this.favoriteRepository = favoriteRepository;
        this.carRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getById(Long favoriteId) {
        return favoriteRepository.findById(favoriteId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,favoriteId));
    }

    @Override
    public Favorite create(Long clientId, Long carId) {
        Car car=carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("Car",carId));
        Favorite favorite=new Favorite();
        favorite.setCar(car);
        favorite.setClientId(clientId);
        Set<ConstraintViolation<Favorite>> violations=validator.validate(favorite);
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        return favoriteRepository.save(favorite);
    }

    @Override
    public ResponseEntity<?> delete(Long favoriteId) {
        return favoriteRepository.findById(favoriteId).map(favorite -> {
            favoriteRepository.delete(favorite);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favoriteId));
    }

    @Override
    public List<Favorite> getFavoritesByClient(Long clientId) {
        return favoriteRepository.getCarsFavoritesClientId(clientId);
    }
}
