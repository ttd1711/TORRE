package com.dzung.torre.opportunity.properties;

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
	@JsonProperty("opportunity-api-url")
	private String opportunityApiUrl;
	
	@JsonProperty("opportunity-search-api-url")
	private String opportunitySearchApiUrl;
}
