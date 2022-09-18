package guru.sfg.msscbrewery.web.mappers;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.msscbrewery.domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	BeerDto beerToBeerDtoWithInventory(Beer beer);

	Beer beerDtoToBeer(BeerDto dto);

}
