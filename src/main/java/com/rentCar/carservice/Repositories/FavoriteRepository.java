package com.rentCar.carservice.Repositories;

import com.rentCar.carservice.Entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    @Query(value="select*from favorites where client_id=?1", nativeQuery = true)
    List<Favorite> getCarsFavoritesClientId(Long clientIdId);
}
