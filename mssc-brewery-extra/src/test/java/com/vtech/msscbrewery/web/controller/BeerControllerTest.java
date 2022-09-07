package com.vtech.msscbrewery.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtech.msscbrewery.domain.Beer;
import com.vtech.msscbrewery.repositories.BeerRepository;
import com.vtech.msscbrewery.services.BeerService;
import com.vtech.msscbrewery.web.mappers.BeerMapper;
import com.vtech.msscbrewery.web.model.BeerDTO;
import com.vtech.msscbrewery.web.model.BeerStyleEnum;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;



@AutoConfigureRestDocs(uriScheme = "http", uriHost="localhost", uriPort=8080)
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.vtech.msscbrewery.web.mappers")
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

//	@MockBean
//	private BeerService beerService;

	@MockBean
	private BeerRepository beerRepository;

	@Test
	void getBeerById() throws Exception {
		
//		BeerDTO employee = new BeerDTO(null, null,null,null,"Tuborg",BeerStyleEnum.TUBORG_STRONG,(long) 1234234234,new BigDecimal(170),12);
//	    List<BeerDTO> employees = Arrays.asList(employee);
//	    Mockito.when(beerService.getBeerById(UUID.randomUUID())).thenReturn(employee);
		
		given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

		this.mockMvc
				.perform(get("/api/v1/beer/{beerId}","f4336745-bf1d-4e90-aac0-7fb1fe47f4bc").param("isCold", "yes")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("v1/beer-get",
						pathParameters(parameterWithName("beerId").description("UUID of desired beer to get.")),
						requestParameters(parameterWithName("isCold").description("is Beer cold query param")),
		                responseFields(
	                                fieldWithPath("id").description("Id of Beer"),
	                                fieldWithPath("version").description("Version number"),
	                                fieldWithPath("createdDate").description("Date Created"),
	                                fieldWithPath("lastModifiedDate").description("Date Updated"),
	                                fieldWithPath("beerName").description("Beer Name"),
	                                fieldWithPath("beerStyle").description("Beer Style"),
	                                fieldWithPath("upc").description("UPC of Beer"),
	                                fieldWithPath("price").description("Price"),
	                                fieldWithPath("quantityOnHand").description("Quantity On hand"))
		                ));
	}

	@Test
	void saveBeer() throws Exception {
		BeerDTO beerDto = getValidBeerDTO();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
				.andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                        requestFields(
                        		fields.withPath("id").ignored(),                                // (before using contraints) fieldWithPath("version").ignored(),
                        		fields.withPath("version").ignored(),
                        		fields.withPath("createdDate").ignored(),
                        		fields.withPath("lastModifiedDate").ignored(),
                        		fields.withPath("beerName").description("Name of the beer"),
                        		fields.withPath("beerStyle").description("Style of Beer"),
                        		fields.withPath("upc").description("Beer UPC").attributes(),
                        		fields.withPath("price").description("Beer Price"),
                        		fields.withPath("quantityOnHand").ignored()
                        )));
	}

	@Test
	void updateBeer() throws Exception {
		BeerDTO beerDto = getValidBeerDTO();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(put("/api/v1/beer/{beerId}" ,"5fca1a16-7caa-4889-90c0-4d5edde4153b").contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson)).andExpect(status().isNoContent());
	}
//
//	@Test
//	void deleteBeer() {
//	}
//
	BeerDTO getValidBeerDTO() {
		return BeerDTO.builder().beerName("Tuborg").beerStyle(BeerStyleEnum.TUBORG_STRONG).upc((long) 1234)
				.price(new BigDecimal(170)).build();
	}
	
	//after adding constraints in test/resources/org/springframework/restdocs/template/request-fields.snippet   (constraints = annotated conditions ex @Notnull...)
	 private static class ConstrainedFields {

	        private final ConstraintDescriptions constraintDescriptions;

	        ConstrainedFields(Class<?> input) {
	            this.constraintDescriptions = new ConstraintDescriptions(input);
	        }

	        private FieldDescriptor withPath(String path) {
	            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
	                    .collectionToDelimitedString(this.constraintDescriptions
	                            .descriptionsForProperty(path), ". ")));
	        }
	    }
}