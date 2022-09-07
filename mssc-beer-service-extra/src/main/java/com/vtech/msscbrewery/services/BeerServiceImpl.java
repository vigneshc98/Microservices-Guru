package com.vtech.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vtech.msscbrewery.domain.Beer;
import com.vtech.msscbrewery.exceptionhandler.NotFoundException;
import com.vtech.msscbrewery.repositories.BeerRepository;
import com.vtech.msscbrewery.web.mappers.BeerMapper;
import com.vtech.msscbrewery.web.model.BeerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getById(UUID beerId) {
		return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto BeerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.BeerDtoToBeer(BeerDto)));
	}

	@Override
	public BeerDto updateBeer(BeerDto BeerDto) {
		Beer beer = beerRepository.findById(BeerDto.getId()).orElseThrow(NotFoundException::new);
		beer.setBeerName(BeerDto.getBeerName());
		beer.setBeerStyle(BeerDto.getBeerStyle().name());
		beer.setPrice(BeerDto.getPrice());
		beer.setUpc(BeerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public String deleteBeer(UUID beerId) {
		// todo: implement logic
		log.debug("deleting a beer....");
		beerRepository.deleteById(beerId);
		return "deleted";
	}
}
