package com.dzung.torre.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dzung.torre.opportunity.object.OpportunityAggregator;
import com.dzung.torre.opportunity.object.GenomeApiObj;
import com.dzung.torre.opportunity.object.OpportunitiesApiObj;
import com.dzung.torre.opportunity.object.OpportunityApiObj;
import com.dzung.torre.opportunity.object.OpportunitySearchRequest;
import com.dzung.torre.opportunity.object.PeopleApiObj;
import com.dzung.torre.opportunity.object.PeopleSearchRequest;
import com.dzung.torre.opportunity.service.OpportunityService;
import com.dzung.torre.opportunity.service.PeopleService;
import com.dzung.torre.properties.AppProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MainController {
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private OpportunityService opportunityService;
	
	@Autowired
	private PeopleService peopleService;

	@GetMapping("/")
	public String pageIndex(Model model) {
		model.addAttribute("appName", appProperties.getPage().getName());

		return "index";
	}

	@GetMapping("/opportunities/{id}")
	public String pageOpportunityById(@PathVariable(name = "id") final String opId
									,Model model) {
		model.addAttribute("appName", appProperties.getPage().getName());
		model.addAttribute("apiOppotunityDetailUrl", appProperties.getApi().getOpportunityDetailUrl());
		model.addAttribute("opId", opId);
		
		return "opportunityDetail";
	}

	@PostMapping("/opportunities/{id}")
	public String ajaxOpportunityById(@PathVariable(name = "id") final String opId
									,Model model) {
		
		OpportunityApiObj opportunityDetail = opportunityService.findOpportunityById(opId);
		model.addAttribute("opportunityDetail", opportunityService.anlyzeOpportunityApiObj(opportunityDetail));
		
		//get cover background image
		String coverPath = "";
		for(OpportunityApiObj.Attachment attachmentObj : opportunityDetail.getAttachments()) {
			if(attachmentObj.getResource().equalsIgnoreCase("cover")) {
				coverPath = attachmentObj.getAddress();
				break;
			}
		}
		model.addAttribute("coverPath", coverPath);
		
		return "opportunities/opportunityDetailContent";
	}

	@GetMapping("/opportunities")
	public String pageOpportunities(Model model) {
		model.addAttribute("appName", appProperties.getPage().getName());
		model.addAttribute("apiOppotunitiesSearchBoxUrl", appProperties.getApi().getOpportunitiesAjaxSearchBoxUrl());
		model.addAttribute("apiOppotunitiesUrl", appProperties.getApi().getOpportunitiesAjaxUrl());
		model.addAttribute("currentPageNumber", 1);
		model.addAttribute("pageSize", appProperties.getApi().getPageSize());
		
		return "opportunities";
	}

	@PostMapping("/opportunities/searchBox")
	public String boxOpportunitiesAggregator(Model model) {
		// get search criteria
		OpportunitySearchRequest opportunityRequest = new OpportunitySearchRequest();
		opportunityRequest.setAggregate(true);

		OpportunitiesApiObj opportunityAggregate = opportunityService.findOpportunities(opportunityRequest);

		// sort Organization by Total
		List<OpportunityAggregator.Organization> organizationCondList = opportunityAggregate.getAggregators().getOrganization();
		Collections.sort(organizationCondList,
				Comparator.comparing(OpportunityAggregator.Organization::getTotal, Comparator.reverseOrder()));
		// add to template
		model.addAttribute("organizationCondList", organizationCondList);
		model.addAttribute("opportunityAggregate", opportunityAggregate);

		return "opportunities/searchCondBox";
	}

	@PostMapping("/opportunities/search/{page}/{page-size}")
	public String searchOpportunities(	@PathVariable(name = "page") final int pageNumber,
										@PathVariable(name = "page-size") final int pageSize, 
										Model model) {
		// get search criteria
		OpportunitySearchRequest opportunityRequest = new OpportunitySearchRequest();
		opportunityRequest.setAggregate(false);
		opportunityRequest.setSize(pageSize);
		opportunityRequest.setOffset((pageNumber-1)*pageSize);

		OpportunitiesApiObj opportunities = opportunityService.findOpportunities(opportunityRequest);

		// add to template
		model.addAttribute("opportunities", opportunities);
		model.addAttribute("currentPageNumber", pageNumber);
		model.addAttribute("pageSize", appProperties.getApi().getPageSize());

		return "opportunities/searchContentContainer";
	}
	

	@GetMapping("/people")
	public String pagePeople(Model model) {
		model.addAttribute("appName", appProperties.getPage().getName());
		model.addAttribute("apiPeopleSearchBoxUrl", appProperties.getApi().getPeopleAjaxSearchBoxUrl());
		model.addAttribute("apiPeopleUrl", appProperties.getApi().getPeopleAjaxUrl());
		model.addAttribute("currentPageNumber", 1);
		model.addAttribute("pageSize", appProperties.getApi().getPageSize());
		
		return "people";
	}
	
	@GetMapping("/people/{id}")
	public String pagePeopleById(@PathVariable(name = "id") final String peopleId
									,Model model) {
		model.addAttribute("appName", appProperties.getPage().getName());
		model.addAttribute("apiPeopleDetailUrl", appProperties.getApi().getPeopleDetailUrl());
		model.addAttribute("peopleId", peopleId);
		
		return "peopleDetail";
	}
	

	@PostMapping("/people/searchBox")
	public String boxPeopleAggregator(Model model) {
		// get search criteria
		PeopleSearchRequest peopleRequest = new PeopleSearchRequest();
		peopleRequest.setAggregate(true);

		PeopleApiObj peopleAggregate = peopleService.findPeople(peopleRequest);
		model.addAttribute("peopleAggregate", peopleAggregate);

		return "people/searchCondBox";
	}

	@PostMapping("/people/search/{page}/{page-size}")
	public String searchPeople(	@PathVariable(name = "page") final int pageNumber,
										@PathVariable(name = "page-size") final int pageSize, 
										Model model) {
		// get search criteria
		PeopleSearchRequest peopleRequest = new PeopleSearchRequest();
		peopleRequest.setAggregate(false);
		peopleRequest.setSize(pageSize);
		peopleRequest.setOffset((pageNumber-1)*pageSize);

		PeopleApiObj people = peopleService.findPeople(peopleRequest);

		// add to template
		model.addAttribute("people", people);
		model.addAttribute("currentPageNumber", pageNumber);
		model.addAttribute("pageSize", appProperties.getApi().getPageSize());

		return "people/searchContentContainer";
	}


	@PostMapping("/people/{id}")
	public String ajaxPeopleById(@PathVariable(name = "id") final String peopleId
									,Model model) {
		
		GenomeApiObj peopleDetail = peopleService.findPeopleById(peopleId);
		model.addAttribute("peopleDetail", peopleService.anlyzePeopleApiObj(peopleDetail));
		
		
		return "people/peopleDetailContent";
	}
}
