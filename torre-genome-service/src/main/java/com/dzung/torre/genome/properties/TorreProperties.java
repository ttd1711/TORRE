package com.dzung.torre.genome.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties("torre")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorreProperties {
	@JsonProperty("genome-api-url")
	private String genomeApiUrl;
	
	@JsonProperty("people-search-api-url")
	private String peopleSearchApiUrl;
}
