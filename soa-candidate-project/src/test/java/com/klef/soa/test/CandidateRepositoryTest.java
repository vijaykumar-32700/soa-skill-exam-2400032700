package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Candidate;
import com.klef.soa.repository.CandidateRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CandidateRepositoryTest 
{
    @Autowired
    private CandidateRepository repository;

    @BeforeEach
    void setUp() 
    {
        if (repository.count() == 0) 
        {
            Candidate candidate = new Candidate();
            candidate.setName("pavan");
            candidate.setType("Full Stack Developer");
            candidate.setStatus("Selected");
            candidate.setLocation("kurnool");
            repository.save(candidate);
        }
    }

    @Test
    void testFindAllCandidates() 
    {
        List<Candidate> candidates = repository.findAll();
        assertNotNull(candidates);
        assertFalse(candidates.isEmpty());
        System.out.println("Total Candidates: " + candidates.size());
        for (Candidate c : candidates) 
        {
            System.out.println("Candidate: " + c.getCandidateId() + " - " + c.getName() + " (" + c.getType() + ")");
        }
    }

    @Test
    void testFindCandidateById() 
    {
        // Existing ID
        Candidate existing = repository.findAll().get(0);
        Long existingId = existing.getCandidateId();

        Optional<Candidate> candidate = repository.findById(existingId);
        assertTrue(candidate.isPresent());
        assertEquals(existingId, candidate.get().getCandidateId());
        System.out.println("Found Candidate ID: " + candidate.get().getCandidateId());
        System.out.println("Found Candidate Name: " + candidate.get().getName());

        // Non-existing ID
        Optional<Candidate> nonExisting = repository.findById(999999L);
        assertFalse(nonExisting.isPresent());
        System.out.println("Non-existing ID 999999 returned empty as expected.");
    }

    @Test
    void testFindCandidateByName() 
    {
        Candidate existing = repository.findAll().get(0);
        String searchName = existing.getName();

        List<Candidate> candidates = repository.findByName(searchName);
        assertNotNull(candidates);
        assertFalse(candidates.isEmpty());
        assertEquals(searchName, candidates.get(0).getName());
        System.out.println("Derived Query Match - Name: " + candidates.get(0).getName());
        System.out.println("Derived Query Match - Type: " + candidates.get(0).getType());
    }

    // Test 4 – Count: Call count() and verify record count.
    @Test
    void testCandidateCount() 
    {
        long count = repository.count();
        assertTrue(count > 0);
        System.out.println("Total Candidates in Database: " + count);
    }

    // Test 5 – Entity Details / Property-Based Result: Verify non-null properties & category query.
    @Test
    void testCandidateDetailsAndCategory() 
    {
        Candidate existing = repository.findAll().get(0);
        Optional<Candidate> candidate = repository.findById(existing.getCandidateId());
        assertTrue(candidate.isPresent());

        Candidate found = candidate.get();
        assertNotNull(found.getCandidateId());
        assertNotNull(found.getName());
        assertNotNull(found.getType());
        assertNotNull(found.getStatus());
        assertNotNull(found.getLocation());

        System.out.println("Verified Details -> Name: " + found.getName() + 
                           ", Type: " + found.getType() + 
                           ", Status: " + found.getStatus() + 
                           ", Location: " + found.getLocation());

        // Category-like property test (findByType)
        List<Candidate> byType = repository.findByType(found.getType());
        assertNotNull(byType);
        assertFalse(byType.isEmpty());
        assertEquals(found.getType(), byType.get(0).getType());
        System.out.println("Category 'Type' matched: " + byType.get(0).getType() + 
                           ", Count: " + byType.size());

        // Category-like property test (findByStatus)
        List<Candidate> byStatus = repository.findByStatus(found.getStatus());
        assertNotNull(byStatus);
        assertFalse(byStatus.isEmpty());
        assertEquals(found.getStatus(), byStatus.get(0).getStatus());
        System.out.println("Category 'Status' matched: " + byStatus.get(0).getStatus() + 
                           ", Count: " + byStatus.size());
    }
}
