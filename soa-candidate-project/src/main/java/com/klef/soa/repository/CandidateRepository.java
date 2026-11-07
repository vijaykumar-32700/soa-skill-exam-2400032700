package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>
{
    List<Candidate> findByName(String name);
    List<Candidate> findByType(String type);
    List<Candidate> findByStatus(String status);
    List<Candidate> findByLocation(String location);
}
