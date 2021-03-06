package com.dzung.torre.opportunity.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityApiObj {
	private List<Attachment> attachments;
	private String locale;
	private String objective;
	private List<Details> details;
	private Place place;
	private String deadline;
	private SerpTags serpTags;	
	private List<Strengths> strengths;
	private List<Organization> organizations;
	private Compensation compensation;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Attachment {
		private String resource;
		private String address;
		private String path;
		private String caption;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Compensation {
		private String code;
		private String currency;
		private boolean estimate;
		private long minAmount;
		private long maxAmount;
		private String periodicity;
		private boolean visible;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Organization {
		private String id;
		private String name;
		private int size;
		private String picture;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Details {
		private String code;
		private String content;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Strengths {
		private String id;
		private String code;
		private String name;
		private String experience;
		private String experienceTitle;

		@Override
		public String toString() {
			return "Strengths [id=" + id + ", code=" + code + ", name=" + name + ", experience=" + experience + "]";
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Place {
		private boolean remote;
		private boolean anywhere;
		private boolean timezone;
		private List<Location> location;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private static class Location {
			private String id;
			private String serpData;
		}
		
	}	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class SerpTags {
		private String validThrough;
		private List<String> employmentType;
		private BaseSalary baseSalary;
		private String description;
		private String title;
		private HiringOrganization hiringOrganization;
		private List<JobLocation> jobLocation;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private static class JobLocation {		
			private Address address;
			
			@Data
			@AllArgsConstructor
			@NoArgsConstructor
			private static class Address {
				private String streetAddress;			
				private String addressLocality;
				private String addressRegion;
				private String addressCountry;
				private String postalCode;
			}
		}
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private static class HiringOrganization {
			private String name;			
			private String logo;
		}
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private static class BaseSalary {
			private String currency;			
			private Value value;
			
			@Data
			@AllArgsConstructor
			@NoArgsConstructor
			private static class Value {
				private long minValue;
				private long maxValue;
				private String unitText;
			}
		}
		
	}

	@Override
	public String toString() {
		return "OpportunityApiObj [locale=" + locale + ", objective=" + objective + ", details=" + details + ", place="
				+ place + ", deadline=" + deadline + ", serpTags=" + serpTags + ", strengths=" + strengths + "]";
	}	
}
