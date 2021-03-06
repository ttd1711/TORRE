package com.dzung.torre.genome.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genomeId;

	@Override
	public String toString() {
		return "Genome [genomeId=" + genomeId + "]";
	}
}
