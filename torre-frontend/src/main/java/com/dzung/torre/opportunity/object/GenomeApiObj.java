package com.dzung.torre.opportunity.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenomeApiObj {
	
	private Person person;
	private List<Strength> strengths;
	private List<Interest> interests;
	private List<Experience> experiences;
	private List<Job> jobs;
	private List<Education> education;
	private List<Opportunity> opportunities;
	private List<Language> languages;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Strength {
		
		private String id;
		private int code;
		private String name;
		private String additionalInfo;
		private int weight;
		private int recommendations;
		private String created;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Interest {
		
		private String id;
		private int code;
		private String name;
		private String created;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Experience {
		
		private String id;
		private String category;
		private String name;
		private List<Organization> organizations;
		private String fromMonth;
		private String fromYear;
		private String toMonth;
		private String toYear;
		private String additionalInfo;
		private boolean highlighted;
		private int weight;
		private int verifications;
		private int recommendations;
		private int rank;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class Organization {
			
			private String id;
			private String name;
			private String picture;
			
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Job {
		
		private String id;
		private String category;
		private String name;
		private List<Organization> organizations;
		private String fromMonth;
		private String fromYear;
		private String toMonth;
		private String toYear;
		private String additionalInfo;
		private boolean highlighted;
		private int weight;
		private int verifications;
		private int recommendations;
		private int rank;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class Organization {
			
			private String id;
			private String name;
			private String picture;
			
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Education {
		
		private String id;
		private String category;
		private String name;
		private List<Organization> organizations;
		private String fromMonth;
		private String fromYear;
		private String toMonth;
		private String toYear;
		private String additionalInfo;
		private boolean highlighted;
		private int weight;
		private int verifications;
		private int recommendations;
		private int rank;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class Organization {
			
			private String id;
			private String name;
			private String picture;
			
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Opportunity {
		
		private String id;
		private String interest;
		private String field;
		//private boolean data;
		
	}
	

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Language {
		
		private String code;
		private String language;
		private String fluency;
		
	}
}

