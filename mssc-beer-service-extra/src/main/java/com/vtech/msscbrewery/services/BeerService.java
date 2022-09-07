package com.vtech.msscbrewery.services;

import com.vtech.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto BeerDTO);

    BeerDto updateBeer(BeerDto BeerDTO);
    
    String deleteBeer(UUID beerId);
}
