package guru.sfg.msscbrewery.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.sfg.msscbrewery.web.model.BeerDto;
import guru.sfg.msscbrewery.web.model.BeerPagedList;
import guru.sfg.msscbrewery.web.model.BeerStyleEnum;

public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
    
    BeerDto getByUpc(String upc);
}
