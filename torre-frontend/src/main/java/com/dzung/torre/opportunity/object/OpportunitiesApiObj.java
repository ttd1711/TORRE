package com.dzung.torre.opportunity.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpportunitiesApiObj {
	
	private OpportunityAggregator aggregators;
	private List<ResultOpportinity> results;
	private int offset;
	private int size;
	private int total;
	
	@Override
	public String toString() {
		return "OpportunitiesApiObj [aggregators=" + aggregators + ", results=" + results + ", offset=" + offset + ", size="
				+ size + ", total=" + total + "]";
	}
	
}
