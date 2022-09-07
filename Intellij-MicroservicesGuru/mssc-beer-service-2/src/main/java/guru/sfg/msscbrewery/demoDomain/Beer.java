package guru.sfg.msscbrewery.demoDomain;

import java.util.UUID;

import guru.sfg.msscbrewery.web.model.BeerStyleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beer {

	private UUID id;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private Long upc;
}
