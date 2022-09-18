package guru.sfg.msscbrewery.web.mappers;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.msscbrewery.domain.Beer;
import guru.sfg.msscbrewery.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
	private BeerInventoryService beerInventoryService;
	private BeerMapper mapper;

	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}

	@Autowired
	public void setMapper(BeerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		return mapper.beerToBeerDto(beer);
	}

	@Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) {
		BeerDto dto = mapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
		return dto;
	}

	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return mapper.beerDtoToBeer(beerDto);
	}
}
