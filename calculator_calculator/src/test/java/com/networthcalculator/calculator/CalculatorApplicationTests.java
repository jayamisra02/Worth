package com.networthcalculator.calculator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void checkForAssetsWithoutAuth() throws Exception {
		mockMvc.perform(get("/assets")).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test
	public void checkForAssets() throws Exception {
		mockMvc.perform(get("/assets").header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("Chequing")));
	}

	@Test
	public void checkForLiabilitiesWithoutAuth() throws Exception {
		mockMvc.perform(get("/liabilities")).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test
	public void checkForLiabilities() throws Exception {
		mockMvc.perform(get("/liabilities").header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("Credit")));
	}

	@Test
	public void deleteAsset() throws Exception {
		mockMvc.perform(delete("/assets/Chequing").header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void deleteLiability() throws Exception {
		mockMvc.perform(delete("/liabilities/Mortgage 1").header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void addAsset() throws Exception {
		String exampleAssetJson = "{\"assetName\":\"Savings\",\"assetValue\":\"2000\"}";

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/assets").header(HttpHeaders.AUTHORIZATION,
						"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))
				.accept(MediaType.APPLICATION_JSON).content(exampleAssetJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	
	@Test
	public void addLiability() throws Exception {
		String exampleLiabilityJson = "{\"liabilityName\":\"Liability\",\"liabilityValue\":\"2000\"}";

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/liabilities").header(HttpHeaders.AUTHORIZATION,
						"Basic " + Base64Utils.encodeToString("admin:password".getBytes()))
				.accept(MediaType.APPLICATION_JSON).content(exampleLiabilityJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
}
