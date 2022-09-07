package guru.sfg.msscbrewery.services;

import java.util.UUID;
import java.util.stream.Collectors;

import guru.sfg.msscbrewery.web.mappers.BeerMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.sfg.msscbrewery.domain.Beer;
import guru.sfg.msscbrewery.exceptionhandler.NotFoundException;
import guru.sfg.msscbrewery.repositories.BeerRepository;
import guru.sfg.msscbrewery.web.model.BeerDto;
import guru.sfg.msscbrewery.web.model.BeerPagedList;
import guru.sfg.msscbrewery.web.model.BeerStyleEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ") // spring will automatically generate a key here (caching)
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {

		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			// search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if (Boolean.TRUE.equals(showInventoryOnHand)) {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory)
							.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}

		return beerPagedList;
	}

	@Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {

		if (Boolean.TRUE.equals(showInventoryOnHand)) {
			return beerMapper
					.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		} else {
			return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Cacheable(cacheNames = "beerUpcCache")
	@Override
	public BeerDto getByUpc(String upc) {
		return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
	}
}