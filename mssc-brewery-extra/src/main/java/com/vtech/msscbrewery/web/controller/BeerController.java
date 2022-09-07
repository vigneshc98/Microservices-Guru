package com.vtech.msscbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vtech.msscbrewery.repositories.BeerRepository;
import com.vtech.msscbrewery.services.BeerService;
import com.vtech.msscbrewery.web.mappers.BeerMapper;
import com.vtech.msscbrewery.web.model.BeerDTO;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

//    private final BeerService beerService;
	
	 private final BeerRepository beerRepository;
	 private final BeerMapper beerMapper;

//    public BeerController(BeerService beerService) {
//        this.beerService = beerService;
//    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@NotNull @PathVariable("beerId")UUID beerId){
        return new ResponseEntity<>(beerMapper.beerToBeerDTO(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@Valid @RequestBody BeerDTO beerDTO){
//        BeerDTO saveDto = beerService.saveNewBear(beerDTO);
//        HttpHeaders headers = new HttpHeaders();
//        
//        System.out.println("id:"+saveDto.getId().toString());
//
//        //todo: add host name to url
//        headers.add("Location","/api/v1/beer/"+saveDto.getId().toString());
//    	return new ResponseEntity<>(headers, HttpStatus.CREATED);

    	beerRepository.save(beerMapper.beerDTOToBeer(beerDTO));
    	 return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable("beerId")UUID beerId,@Valid @RequestBody BeerDTO beerDTO ){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDTO.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyle().name());
            beer.setPrice(beerDTO.getPrice());
            beer.setUpc(beerDTO.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/{beerId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteBeer(@PathVariable("beerId")UUID beerId){
//        beerService.deleteBeer(beerId);
//    }
    

}
