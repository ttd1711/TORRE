package com.dzung.torre.genome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzung.torre.genome.object.GenomeApiObj;
import com.dzung.torre.genome.service.GenomeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/genome")
@Slf4j
public class GenomeController {
	
	@Autowired
	private GenomeService genomeService;
	
	@GetMapping("/{username}")
	public GenomeApiObj findGenomeByIUsername(@PathVariable("username") String genomeUsername) {
		log.info("[findGenomeByIUsername] find username = {}", genomeUsername);
		
		return genomeService.findGenomeByIUsername(genomeUsername);
	}
	
}
