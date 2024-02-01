package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="University info from http://universities.hipolabs.com/")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService UniversityQueryService;

    @Operation(summary = "Get information on a university")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversities(
        @Parameter(name="name", description="name of university", example="Harvard") @RequestParam String name
    ) throws JsonProcessingException {
        log.info("getUniversities: name={}", name);
        String result = UniversityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }    

}