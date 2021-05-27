package com.af.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.af.cms.model.Workshop;

@Repository
public interface WorkshopRepository extends MongoRepository<Workshop,Integer> {
	
	

}
