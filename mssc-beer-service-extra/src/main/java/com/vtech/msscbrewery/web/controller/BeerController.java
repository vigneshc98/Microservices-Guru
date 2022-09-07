package com.vtech.msscbrewery.web.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtech.msscbrewery.services.BeerService;
import com.vtech.msscbrewery.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> saveBeer(@Valid @RequestBody BeerDto beerDTO) {

		return new ResponseEntity<>(beerService.saveNewBeer(beerDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDTO) {

		return new ResponseEntity<>(beerService.updateBeer(beerDTO), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
	public String deleteBeer(@PathVariable("beerId") UUID beerId) {
		return beerService.deleteBeer(beerId);
	}

}
