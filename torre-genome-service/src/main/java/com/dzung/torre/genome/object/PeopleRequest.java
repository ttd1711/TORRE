package com.dzung.torre.genome.object;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRequest {
	
	@Min(0)
	private int offset;
	@Min(0)
	private int size;
	private boolean aggregate;
	@Override
	public String toString() {
		return "PeopleRequest [offset=" + offset + ", size=" + size + ", aggregate=" + aggregate + "]";
	}
	
	
}
