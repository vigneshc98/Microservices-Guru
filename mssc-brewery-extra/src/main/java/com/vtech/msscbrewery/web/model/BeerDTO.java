package com.vtech.msscbrewery.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDTO {

	@Null
    private UUID id;
	
	@Null
    private Integer version;
	
	@Null
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
	
	@Null
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min=3,max=100)
    private String beerName;
    
    @NotNull
    private BeerStyleEnum beerStyle;

    @Positive
    @NotNull
    private Long upc;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    private Integer quantityOnHand;
}
