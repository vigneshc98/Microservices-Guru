package guru.sfg.msscbrewery.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import guru.sfg.msscbrewery.domain.Beer;
import guru.sfg.msscbrewery.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	BeerDto beerToBeerDtoWithInventory(Beer beer);

	Beer beerDtoToBeer(BeerDto dto);

}
