package com.rentCar.carservice.Services;

import com.rentCar.carservice.Entities.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getAll();
    Favorite getById(Long favoriteId);
    Favorite create(Long clientId,Long carId);
    ResponseEntity<?> delete(Long favoriteId);
    List<Favorite> getFavoritesByClient(Long clientId);
}
