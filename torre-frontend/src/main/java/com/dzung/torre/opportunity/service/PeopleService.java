package com.dzung.torre.opportunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dzung.torre.opportunity.object.OpportunityApiObj;
import com.dzung.torre.opportunity.object.GenomeApiObj;
import com.dzung.torre.opportunity.object.OpportunitiesApiObj;
import com.dzung.torre.opportunity.object.OpportunitySearchRequest;
import com.dzung.torre.opportunity.object.PeopleApiObj;
import com.dzung.torre.opportunity.object.PeopleSearchRequest;
import com.dzung.torre.properties.AppProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PeopleService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppProperties appProperties;
	
	public PeopleApiObj findPeople(PeopleSearchRequest peopleRequest) {
		PeopleApiObj retObj = null;
		
		String apiUrl = appProperties.getApi().getGwPeopleSearchUrl();
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

	public GenomeApiObj findPeopleById(String Id) {
		GenomeApiObj retOp = null;
		
		String apiUrl = appProperties.getApi().getGwPeopleDetailUrl().replace("{id}", Id);
		log.info("[findPeopleById] find Opportunity = {} by calling API {}", Id, apiUrl);
		
		try {
			if(this.validateOpportunityId(Id)) {
				//call Torre API
				retOp = 
						restTemplate.getForObject(apiUrl, 
								GenomeApiObj.class);
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
			if(Id != null && !Id.equals("")) {
				ret = true; //validated
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[validateOpportunityId] result = ", ret);
			
		}
		
		return ret;
	}
	
	/*
	 *  search criteria validation
	 */
	private boolean validateSearchCriteria(PeopleSearchRequest peopleRequest) {
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
	
	public OpportunityApiObj anlyzeOpportunityApiObj(OpportunityApiObj orgObj) {
		OpportunityApiObj obj = orgObj;
		
		try {
			//process Strengths
			for(OpportunityApiObj.Strengths strItem : obj.getStrengths()) {
				if(strItem.getExperience().equalsIgnoreCase("potential-to-develop")) {
					strItem.setExperienceTitle("Potential To Develop");
				}
				else if(strItem.getExperience().contains("-plus-year")) {
					String[] arrExp = strItem.getExperience().split("-");
					if(arrExp.length>0) {
						strItem.setExperienceTitle(arrExp[0] + "+ years of experience");
					}
				}
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[anlyzeOpportunityApiObj] Done");			
		}
		
		return obj;
	}
	
	
	public GenomeApiObj anlyzePeopleApiObj(GenomeApiObj orgObj) {
		GenomeApiObj obj = orgObj;
		
		try {
			//process Strengths
			
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[anlyzePeopleApiObj] Done");			
		}
		
		return obj;
	}

}
