package guru.sfg.beer.order.service.services;

import guru.sfg.brewery.model.events.CustomerPagedList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerPagedList listCustomer(Pageable pageable);
}
