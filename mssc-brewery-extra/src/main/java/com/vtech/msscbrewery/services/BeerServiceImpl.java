package com.vtech.msscbrewery.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vtech.msscbrewery.web.model.BeerDTO;
import com.vtech.msscbrewery.web.model.BeerStyleEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return BeerDTO.builder()
                .id(beerId)
                .beerName("Tuborg")
                .beerStyle(BeerStyleEnum.TUBORG_STRONG)
                .upc((long)1234)
                .price(new BigDecimal(170))
                .build();
    }

    @Override
    public BeerDTO saveNewBear(BeerDTO beerDTO) {
        return  BeerDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(BeerDTO beerDTO) {
        //todo: implement logic
    }

    @Override
    public void deleteBeer(UUID beerId) {
        //todo: implement logic
        log.debug("deleting a beer....");
    }
}
