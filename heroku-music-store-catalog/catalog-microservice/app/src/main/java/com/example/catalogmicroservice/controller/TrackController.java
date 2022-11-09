package com.example.catalogmicroservice.controller;


import com.example.catalogmicroservice.model.Track;
import com.example.catalogmicroservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/track")
public class TrackController {
    @Autowired
    private TrackRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable Long id){
        Optional<Track> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Track with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() {
        return repo.findAll();
    }

    //CREATE a new track
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Track newTrack(@RequestBody Track track){
        return repo.save(track);
    }

    //UPDATE a track
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Track updateTrack(@RequestBody Track track){
        return repo.save(track);
    }

    //DELETE a track
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Long id){
        repo.deleteById(id);
    }
}
