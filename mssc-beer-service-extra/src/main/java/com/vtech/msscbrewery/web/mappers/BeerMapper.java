package com.vtech.msscbrewery.web.mappers;

import org.mapstruct.Mapper;

import com.vtech.msscbrewery.domain.Beer;
import com.vtech.msscbrewery.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	BeerDto beerToBeerDto(Beer beer);
	
	Beer BeerDtoToBeer(BeerDto BeerDto);

}
