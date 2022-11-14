package com.rentCar.carservice.Shared.Mapping;

import com.rentCar.carservice.Mapping.BrandMapper;
import com.rentCar.carservice.Mapping.CarMapper;
import com.rentCar.carservice.Mapping.FavoriteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration("carServiceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }
    @Bean
    public BrandMapper brandMapper(){return new BrandMapper();}
    @Bean
    public CarMapper carMapper(){return new CarMapper();}
    @Bean
    public FavoriteMapper favoriteMapper(){return new FavoriteMapper();}
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}