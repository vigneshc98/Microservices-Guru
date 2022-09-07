package guru.sfg.msscbrewery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.sfg.msscbrewery.services.inventory.BeerInventoryService;

@SpringBootTest
class MsscBeerService2ApplicationTests {
	
    @Autowired
    BeerInventoryService beerInventoryService;

	@Test
	void contextLoads() {
	}
	
//    @Test
//    void getOnhandInventory() {
//        Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);
//
//        System.out.println(qoh);
//
//    }

}
