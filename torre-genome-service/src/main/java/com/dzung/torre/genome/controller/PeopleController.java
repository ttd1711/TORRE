package com.dzung.torre.genome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzung.torre.genome.object.GenomeApiObj;
import com.dzung.torre.genome.object.PeopleApiObj;
import com.dzung.torre.genome.object.PeopleRequest;
import com.dzung.torre.genome.service.GenomeService;
import com.dzung.torre.genome.service.PeopleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/people")
@Slf4j
public class PeopleController {
	
	@Autowired
	private PeopleService peopleService;
	
	@PostMapping("/_search")
	public PeopleApiObj findPeople(PeopleRequest peopleRequest) {
		log.info("[findPeople] request = {}", peopleRequest.toString());
		
		return peopleService.findPeople(peopleRequest);
	}
	
}
