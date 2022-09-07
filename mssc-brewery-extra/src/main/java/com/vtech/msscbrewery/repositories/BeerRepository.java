package com.vtech.msscbrewery.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vtech.msscbrewery.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
