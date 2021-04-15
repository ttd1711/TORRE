package com.dzung.torre.genome.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenomeApiObj {
	
	private Person person;

	@Override
	public String toString() {
		return "Genome [person=" + person.toString() + "]";
	}
	
}