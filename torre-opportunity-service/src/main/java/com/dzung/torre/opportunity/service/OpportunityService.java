package com.dzung.torre.opportunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dzung.torre.opportunity.object.OpportunityApiObj;
import com.dzung.torre.opportunity.object.OpportunitiesApiObj;
import com.dzung.torre.opportunity.object.OpportunitySearchRequest;
import com.dzung.torre.opportunity.properties.TorreProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpportunityService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TorreProperties torreProperties;
	
	public OpportunitiesApiObj findOpportunities(OpportunitySearchRequest opportunityRequest) {
		OpportunitiesApiObj retObj = null;
		
		String apiUrl = torreProperties.getOpportunitySearchApiUrl();
		log.info("[findOpportunities] find opportunities by calling API {}", apiUrl);
		
		try {
			if(this.validateSearchCriteria(opportunityRequest)) {
				String apiFullUrl = apiUrl + "?" 
						+ "offset=" + opportunityRequest.getOffset()
						+ "&size=" + opportunityRequest.getSize()
						+ "&aggregate=" + opportunityRequest.isAggregate();
				log.info("[findOpportunities] API = {}", apiFullUrl);
				
				//set header to json
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				//call API
				retObj = 
						restTemplate.postForObject(apiFullUrl, new HttpEntity<>(null, headers), OpportunitiesApiObj.class);
				//
				log.info("API Result: " + retObj.toString());
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
				
		
		return retObj;
	}

	public OpportunityApiObj findOpportunityById(String Id) {
		OpportunityApiObj retOp = null;
		
		String apiUrl = torreProperties.getOpportunityApiUrl() + Id;
		log.info("[findOpportunityById] find Opportunity = {} by calling API {}", Id, apiUrl);
		
		try {
			if(this.validateOpportunityId(Id)) {
				//call Torre API
				retOp = 
						restTemplate.getForObject(apiUrl, 
								OpportunityApiObj.class);
				//
				log.info("API Result: " + retOp.toString());
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
				
		
		return retOp;
	}

	/*
	 *  Id validation
	 */
	private boolean validateOpportunityId(String Id) {
		boolean ret = false;
		
		try {
			//check Id valid
			if(Id!=null & !Id.equals("")) {
				ret = true; //validated
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[validateOpportunityId] result = {}", (ret ? "true" : "false"));
		}
		
		return ret;
	}
	
	/*
	 *  search criteria validation
	 */
	private boolean validateSearchCriteria(OpportunitySearchRequest peopleRequest) {
		boolean ret = true;
		
		try {
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
