package com.dzung.torre.genome.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aggregator {
	private List<OpenTo> opento;
	private List<Remoter> remoter;
	private List<Skill> skill;
	private List<CompensationRange> compensationrange;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class OpenTo {
		private int total;
		private String value;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Remoter {
		private int total;
		private String value;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Skill {
		private int total;
		private String value;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class CompensationRange {
		private int total;
		private String value;
	}}
