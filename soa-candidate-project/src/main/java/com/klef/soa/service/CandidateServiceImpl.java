package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Candidate;
import com.klef.soa.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService
{
    @Autowired
    private CandidateRepository repo;

    @Override
    public Candidate addCandidate(Candidate candidate)
    {
        return repo.save(candidate);
    }

    @Override
    public List<Candidate> displayAllCandidates()
    {
        return repo.findAll();
    }

    @Override
    public Candidate updateCandidate(Candidate c)
    {
        if (c.getCandidateId() == null) {
            return null;
        }

        Optional<Candidate> optional = repo.findById(c.getCandidateId());

        if(optional.isPresent())
        {
            Candidate candidate = optional.get();

            candidate.setName(c.getName());
            candidate.setType(c.getType());
            candidate.setStatus(c.getStatus());
            candidate.setLocation(c.getLocation());

            return repo.save(candidate);
        }
        else
        {
            return null;
        }
    }

    @Override
    public Candidate displayCandidateById(Long candidateId)
    {
        return repo.findById(candidateId).orElse(null);
    }

    @Override
    public String deleteCandidateById(Long candidateId)
    {
        boolean status = repo.existsById(candidateId);

        if(status)
        {
            repo.deleteById(candidateId);
            return "Candidate Deleted Successfully";
        }
        else
        {
            return "Candidate ID Not Found";
        }
    }

    @Override
    public List<Candidate> displayCandidatesByName(String name)
    {
        return repo.findByName(name);
    }

    @Override
    public List<Candidate> displayCandidatesByType(String type)
    {
        return repo.findByType(type);
    }

    @Override
    public List<Candidate> displayCandidatesByStatus(String status)
    {
        return repo.findByStatus(status);
    }
}
