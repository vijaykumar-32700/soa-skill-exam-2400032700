package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Candidate;

public interface CandidateService
{
    Candidate addCandidate(Candidate candidate);
    List<Candidate> displayAllCandidates();
    Candidate updateCandidate(Candidate candidate);
    Candidate displayCandidateById(Long candidateId);
    String deleteCandidateById(Long candidateId);
    List<Candidate> displayCandidatesByName(String name);
    List<Candidate> displayCandidatesByType(String type);
    List<Candidate> displayCandidatesByStatus(String status);
}
