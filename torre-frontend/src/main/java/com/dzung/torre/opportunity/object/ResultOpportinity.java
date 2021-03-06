package com.dzung.torre.opportunity.object;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultOpportinity {
	private String id;
	private String objective;
	private String slug;
	private String type;
	private List<Organization> organizations;
	private List<String> locations;
	private boolean remote;
	private boolean external;
	private String deadline;	
	private String created;
	private String status;	
	private Compensation compensation;
	private List<Skill> skills;
	private List<Members> members;
	private List<Question> questions;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Organization {
		private int id;
		private String name;
		private String picture;
	}
		
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class Compensation {
		private DataInfo data;
		private boolean visible;
		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private class DataInfo {
			private String code;
			private String currency;
			private String minAmount;
			private String maxAmount;
			private String periodicity;
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Skill {
		private String name;
		private String experience;
		@Override
		public String toString() {
			return "Skill [name=" + name + ", experience=" + experience + "]";
		}
	}

	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Members {
		private String subjectId;
		private String name;
		private String username;
		private String professionalHeadline;
		private String picture;
		private boolean member;
		private boolean manager;
		private boolean poster;
		private long weight;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Question {
		private String id;
		private String text;
		private String date;
	}

	@Override
	public String toString() {
		return "ResultOpportinity [id=" + id + ", objective=" + objective + ", slug=" + slug + ", type=" + type
				+ ", organizations=" + organizations + ", locations=" + locations + ", remote=" + remote + ", external="
				+ external + ", deadline=" + deadline + ", created=" + created + ", status=" + status
				+ ", compensation=" + compensation + ", skills=" + skills + ", members=" + members + ", questions="
				+ questions + "]";
	}
	
	
}
