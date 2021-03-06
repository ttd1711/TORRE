package com.dzung.torre.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "app")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppProperties {
	private PageTag page = new PageTag();
	private ApiTag api = new ApiTag();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PageTag {
		private String title;
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ApiTag {
		private String gwOpportunitiesSearchUrl;
		private String gwOpportunitiyDetailUrl;
		private String opportunitiesAjaxSearchBoxUrl;
		private String opportunitiesAjaxUrl;
		private String opportunityDetailUrl;
		
		private String gwPeopleSearchUrl;
		private String gwPeopleDetailUrl;
		private String peopleAjaxSearchBoxUrl;
		private String peopleAjaxUrl;
		private String peopleDetailUrl;
		private int pageSize;
	}
}
