package com.dzung.torre.opportunity.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPeople {
	private Meta _meta;
	private Compensations compensations;
	private String locationName;
	private String name;
	private List<String> openTo;
	private String picture;
	private String professionalHeadline;
	private boolean remoter;
	private List<Skill> skills;
	private String subjectId;
	private String username;
	private boolean verified;
	private long weight;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class Meta {
		private String filter;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class Compensations {
		private CompensationInfo freelancer;
		private CompensationInfo intern;
		private CompensationInfo employee;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private class CompensationInfo {
			private long amount;
			private String currency;
			private String periodicity;
			@Override
			public String toString() {
				return "CompensationInfo [amount=" + amount + ", currency=" + currency + ", periodicity=" + periodicity
						+ "]";
			}
		}

		@Override
		public String toString() {
			return "Compensations [freelancer=" + freelancer + ", intern=" + intern + ", employee=" + employee + "]";
		}
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Skill {
		private String name;
		private long weight;
		@Override
		public String toString() {
			return "Skill [name=" + name + ", weight=" + weight + "]";
		}
	}

	@Override
	public String toString() {
		return "ResultPeople [_meta=" + _meta + ", compensations=" + compensations + ", locationName=" + locationName
				+ ", name=" + name + ", openTo=" + openTo + ", picture=" + picture + ", professionalHeadline="
				+ professionalHeadline + ", remoter=" + remoter + ", skills=" + skills + ", subjectId=" + subjectId
				+ ", username=" + username + ", verified=" + verified + ", weight=" + weight + "]";
	}
	
	
}
