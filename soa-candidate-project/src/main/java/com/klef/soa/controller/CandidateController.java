package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Candidate;
import com.klef.soa.service.CandidateService;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "*")
public class CandidateController
{
    @Autowired
    private CandidateService service;

    @GetMapping("/")
    public String test()
    {
        return "SOA Candidate REST API";
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate c)
    {
        Candidate candidate = service.addCandidate(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate);
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<Candidate>> displayAllCandidates()
    {
        List<Candidate> candidates = service.displayAllCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }

    @GetMapping("/display")
    public ResponseEntity<?> displayCandidateByIdParam(@RequestParam Long candidateId)
    {
        Candidate candidate = service.displayCandidateById(candidateId);

        if(candidate != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(candidate);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate ID Not Found");
        }
    }

    @GetMapping("/display/{candidateId}")
    public ResponseEntity<?> displayCandidateByIdPath(@PathVariable Long candidateId)
    {
        Candidate candidate = service.displayCandidateById(candidateId);

        if(candidate != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(candidate);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate ID Not Found");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate candidate)
    {
        Candidate c = service.updateCandidate(candidate);

        if(c != null)
        {
            return ResponseEntity.ok(c);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate ID Not Found");
        }
    }

    @DeleteMapping("/delete/{candidateId}")
    public ResponseEntity<String> deleteCandidateById(@PathVariable Long candidateId)
    {
        String message = service.deleteCandidateById(candidateId);
        if("Candidate Deleted Successfully".equals(message))
        {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<Candidate>> displayCandidatesByName(@PathVariable String name)
    {
        List<Candidate> candidates = service.displayCandidatesByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }

    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<Candidate>> displayCandidatesByType(@PathVariable String type)
    {
        List<Candidate> candidates = service.displayCandidatesByType(type);
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }

    @GetMapping("/displaybystatus/{status}")
    public ResponseEntity<List<Candidate>> displayCandidatesByStatus(@PathVariable String status)
    {
        List<Candidate> candidates = service.displayCandidatesByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(candidates);
    }
}
