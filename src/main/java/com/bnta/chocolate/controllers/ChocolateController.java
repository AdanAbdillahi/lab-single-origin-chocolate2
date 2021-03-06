package com.bnta.chocolate.controllers;


import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chocolates")
public class ChocolateController {

    @Autowired
    ChocolateRepository chocolateRepository;

    // Get
    @GetMapping   // localhost:8080/chocolates
    public ResponseEntity<List<Chocolate>> getAllChocolateAndFilters(
            @RequestParam(required = false, name = "name") String type
    ) {
        if(type != null){
            return new ResponseEntity<>(chocolateRepository.findChocolateByName(type), HttpStatus.OK);

        }
        return new ResponseEntity<>(chocolateRepository.findAll(), HttpStatus.OK);
    }

    // Show
    @GetMapping(value = "/{id}") // localhost:8080/chocolates/1
    public ResponseEntity<Optional<Chocolate>> getChocolate(@PathVariable Long id){
        return new ResponseEntity<>(chocolateRepository.findById(id), HttpStatus.OK
        );
    }

    //     Post
    @PostMapping // POST localhost:8080/chocolates
    public ResponseEntity<Chocolate> createChocolate(@RequestBody Chocolate newChocolate){
        chocolateRepository.save(newChocolate);
        return new ResponseEntity<>(newChocolate, HttpStatus.CREATED);
    }

}
