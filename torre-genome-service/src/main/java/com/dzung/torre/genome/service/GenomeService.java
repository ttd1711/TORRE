package com.dzung.torre.genome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dzung.torre.genome.object.GenomeApiObj;
import com.dzung.torre.genome.properties.TorreProperties;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenomeService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TorreProperties torreProperties;
	
	public GenomeApiObj findGenomeByIUsername(String genomeUsername) {
		GenomeApiObj retGenome = null;
		
		String apiUrl = torreProperties.getGenomeApiUrl();
		log.info("[findGenomeByIUsername] find username = {} by calling API {}", genomeUsername, apiUrl);
		
		try {
			if(this.validateGenomeUsername(genomeUsername)) {
				//call Torre API
				retGenome = 
						restTemplate.getForObject(apiUrl + genomeUsername, 
								GenomeApiObj.class);
				//
				log.info("API Result: " + retGenome.toString());
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
				
		
		return retGenome;
	}

	/*
	 *  Username validation
	 */
	private boolean validateGenomeUsername(String genomeUsername) {
		boolean ret = false;
		
		try {
			//check username empty or null
			if(genomeUsername!=null && !genomeUsername.equals("")) {
				ret = true; //validated
			}
		}
		catch(Exception ex) {
			log.error("Exception: {}", ex.getMessage());
		}
		finally{
			log.info("[validateGenomeUsername] result = ", ret);
			
		}
		
		return ret;
	}
}
