package com.dzung.torre.genome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dzung.torre.genome.object.GenomeApiObj;
import com.dzung.torre.genome.object.PeopleApiObj;
import com.dzung.torre.genome.object.PeopleRequest;
import com.dzung.torre.genome.properties.TorreProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PeopleService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TorreProperties torreProperties;
	
	public PeopleApiObj findPeople(PeopleRequest peopleRequest) {
		PeopleApiObj retObj = null;
		
		String apiUrl = torreProperties.getPeopleSearchApiUrl();
		log.info("[findPeople] find people by calling API {}", apiUrl);
		
		try {
			if(this.validateSearchCriteria(peopleRequest)) {
				String apiFullUrl = apiUrl + "?" 
						+ "offset=" + peopleRequest.getOffset()
						+ "&size=" + peopleRequest.getSize()
						+ "&aggregate=" + peopleRequest.isAggregate();
				log.info("[findPeople] API = {}", apiFullUrl);
				
				//set header to json
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				//call API
				retObj = 
						restTemplate.postForObject(apiFullUrl, new HttpEntity<>(null, headers), PeopleApiObj.class);
				//
				log.info("API Result: " + retObj.toString());
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
				
		
		return retObj;
	}

	/*
	 *  search criteria validation
	 */
	private boolean validateSearchCriteria(PeopleRequest peopleRequest) {
		boolean ret = true;
		
		try {
			//check username empty or null
			if(peopleRequest.getOffset()<0) {
				ret = false; 
			}
			else if(peopleRequest.getSize()<0) {
				ret = false; 
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[validateSearchCriteria] result = ", ret);
			
		}
		
		return ret;
	}
}
