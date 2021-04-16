package com.dzung.torre.opportunity.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleApiObj {
	
	private PeopleAggregator aggregators;
	private List<ResultPeople> results;
	private int offset;
	private int size;
	private int total;
	
	@Override
	public String toString() {
		return "PeopleApiObj [aggregators=" + aggregators + ", results=" + results + ", offset=" + offset + ", size="
				+ size + ", total=" + total + "]";
	}
	
}
