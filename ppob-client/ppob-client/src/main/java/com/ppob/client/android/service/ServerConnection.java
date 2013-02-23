package com.ppob.client.android.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ppob.client.android.domain.Product;

public class ServerConnection {
	private String urlProductList;
	
	public ServerConnection(String ipServer, String portServer){
		urlProductList = "http://"+ipServer+":"+portServer+"/master/product";
	}
	
	public List<Product> getProductData(){
		// Set the Accept header for "application/json"
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(acceptableMediaTypes);

		// Populate the headers in an HttpEntity object to use for the request
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		
		// Perform the HTTP GET request
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(urlProductList, HttpMethod.GET, requestEntity, Product[].class);
		
		// convert the array to a list and return it
		return Arrays.asList(responseEntity.getBody());
	}
	
}
