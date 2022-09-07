package com.vtech.msscbrewery.web.mappers;

import org.mapstruct.Mapper;

import com.vtech.msscbrewery.domain.Beer;
import com.vtech.msscbrewery.web.model.BeerDTO;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	BeerDTO beerToBeerDTO(Beer beer);
	
	Beer beerDTOToBeer(BeerDTO beerDTO);

}
