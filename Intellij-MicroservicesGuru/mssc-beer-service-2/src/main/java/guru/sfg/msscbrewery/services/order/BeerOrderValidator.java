package guru.sfg.msscbrewery.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.msscbrewery.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger beerNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(orderLine->{
            if(beerRepository.findByUpc(orderLine.getUpc())==null){
                beerNotFound.incrementAndGet();
            }
        });
        return beerNotFound.get() == 0;
    }
}
