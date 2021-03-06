package com.dzung.torre.opportunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dzung.torre.opportunity.entity.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
	
}
