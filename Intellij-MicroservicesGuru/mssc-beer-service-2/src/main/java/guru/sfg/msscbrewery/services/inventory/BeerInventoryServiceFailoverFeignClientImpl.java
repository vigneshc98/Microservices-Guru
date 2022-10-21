package guru.sfg.msscbrewery.services.inventory;

import guru.sfg.msscbrewery.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BeerInventoryServiceFailoverFeignClientImpl implements InventoryServiceFeignClient{

    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return inventoryFailoverFeignClient.getOnhandInventory();
    }
}
