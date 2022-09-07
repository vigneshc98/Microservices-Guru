package com.vtech.msscbrewery.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vtech.msscbrewery.domain.Beer;
import com.vtech.msscbrewery.repositories.BeerRepository;
import com.vtech.msscbrewery.web.model.BeerStyleEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final Long BEER_1_UPC = 0631234200036L;
    public static final Long BEER_2_UPC = 0631234201036L;
    public static final Long BEER_3_UPC = 0631214200036L;

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {

          if(beerRepository.count() == 0 ) {
              loadBeerObjects();
          }
    }

    private void loadBeerObjects() {
        Beer b1 = Beer.builder()
                .beerName("BlacFort")
                .beerStyle(BeerStyleEnum.BLACKFORT_STRONG.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Budwiser")
                .beerStyle(BeerStyleEnum.BUDWISER_MAGNUM.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("TuborgStrong")
                .beerStyle(BeerStyleEnum.TUBORG_STRONG.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC)
                .build();

        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);
    }
}