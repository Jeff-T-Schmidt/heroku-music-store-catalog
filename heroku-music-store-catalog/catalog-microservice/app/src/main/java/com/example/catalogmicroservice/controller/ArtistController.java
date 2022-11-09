package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Artist;
import com.example.catalogmicroservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/artist")
public class ArtistController {
    @Autowired
    private ArtistRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable Long id){
        Optional<Artist> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Artist with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return repo.findAll();
    }

    //CREATE a new artist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist newArtist(@RequestBody Artist artist){
        return repo.save(artist);
    }

    //UPDATE an artist
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Artist updateArtist(@RequestBody Artist artist){
        return repo.save(artist);
    }

    //DELETE an artist
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long id){
        repo.deleteById(id);
    }
}
