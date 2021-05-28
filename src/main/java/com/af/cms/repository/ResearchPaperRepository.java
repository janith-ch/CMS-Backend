package com.af.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.af.cms.model.ResearchPaper;

@Repository
public interface ResearchPaperRepository  extends MongoRepository<ResearchPaper,Integer> {

}
