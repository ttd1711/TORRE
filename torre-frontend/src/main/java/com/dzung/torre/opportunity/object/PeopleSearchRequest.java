package com.dzung.torre.opportunity.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleSearchRequest {
	
	private int offset;
	private int size;
	private boolean aggregate;
	@Override
	public String toString() {
		return "PeopleRequest [offset=" + offset + ", size=" + size + ", aggregate=" + aggregate + "]";
	}
	
	
}
