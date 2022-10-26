package com.rentCar.carservice.Controllers;

import com.rentCar.carservice.Mapping.FavoriteMapper;
import com.rentCar.carservice.Resource.FavoriteResource;
import com.rentCar.carservice.Services.FavoriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorites")
@CrossOrigin
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final FavoriteMapper mapper;

    public FavoriteController(FavoriteService favoriteService, FavoriteMapper mapper) {
        this.favoriteService = favoriteService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<FavoriteResource> getAllFavorites(Pageable pageable){
        return mapper.modelListToPage(favoriteService.getAll(),pageable);
    }
    @GetMapping("{favoriteId}")
    public FavoriteResource getFavoriteById(@PathVariable Long favoriteId){
        return  mapper.toResource(favoriteService.getById(favoriteId));
    }
    @PostMapping("client/{clientId}/car/{carId}")
    public FavoriteResource createFavorite(@PathVariable("clientId")Long clientId,
                                           @PathVariable("carId")Long carId){
        return mapper.toResource(favoriteService.create(clientId,carId));
    }
    @DeleteMapping("{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long favoriteId) {
        return favoriteService.delete(favoriteId);
    }
    @GetMapping("client/{clientId}")
    public Page<FavoriteResource> getFavoriteByClientId(@PathVariable Long clientId,Pageable pageable){
        return mapper.modelListToPage(favoriteService.getFavoritesByClient(clientId),pageable);
    }
}
