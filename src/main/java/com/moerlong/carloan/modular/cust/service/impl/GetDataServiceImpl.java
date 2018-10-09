package com.moerlong.carloan.modular.cust.service.impl;

import com.moerlong.carloan.modular.cust.service.GetDataService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GetDataServiceImpl implements GetDataService{
	public String client(String url, HttpMethod method){
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response= template.getForEntity(url,String.class);
		if(response.getStatusCode().equals(HttpStatus.OK)){
			return response.getBody();
		}
		return "-1";
	}
}

