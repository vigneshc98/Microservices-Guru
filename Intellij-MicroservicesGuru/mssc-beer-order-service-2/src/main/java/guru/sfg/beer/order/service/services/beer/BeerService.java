package guru.sfg.beer.order.service.services.beer;

import java.util.Optional;
import java.util.UUID;

import guru.sfg.beer.order.service.services.beer.model.BeerDto;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}
