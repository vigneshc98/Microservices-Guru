package com.vtech.msscbrewery.services;

import com.vtech.msscbrewery.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {
    public BeerDTO getBeerById(UUID beerId);
    public BeerDTO saveNewBear(BeerDTO beerDTO);
    public void updateBeer(BeerDTO beerDTO);
    public void deleteBeer(UUID beerId);
}
