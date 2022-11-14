package com.rentCar.carservice.Services.impls;

import com.rentCar.carservice.Resource.OwnerCarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCall {
    @Autowired
    RestTemplate restTemplate;
    public OwnerCarResource getOwner(Long ownerId)throws RestClientException {
        return restTemplate.getForObject("http://localhost:8105/api/v1/owners/ownerCar/"+ownerId,
                OwnerCarResource.class);
    }
}
