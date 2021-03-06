package com.dzung.torre.opportunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzung.torre.opportunity.object.OpportunityApiObj;
import com.dzung.torre.opportunity.object.OpportunitiesApiObj;
import com.dzung.torre.opportunity.object.OpportunitySearchRequest;
import com.dzung.torre.opportunity.service.OpportunityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/opportunities")
@Slf4j
public class OpportunityController {
	
	@Autowired
	private OpportunityService opportunityService;
	
	@GetMapping("/{id}")
	public OpportunityApiObj findOpportunityById(@PathVariable("id") String opportunityId) {
		log.info("[findOpportunityById] find Id = {}", opportunityId);
		
		return opportunityService.findOpportunityById(opportunityId);
	}

	@PostMapping("/_search")
	public OpportunitiesApiObj findOpportunities(OpportunitySearchRequest opportunityRequest) {
		log.info("[findOpportunity] request = {}", opportunityRequest.toString());
		
		return opportunityService.findOpportunities(opportunityRequest);
	}
	
}
