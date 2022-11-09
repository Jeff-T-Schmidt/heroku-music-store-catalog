package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Album;
import com.example.catalogmicroservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/album")
public class AlbumController {
    @Autowired
    private AlbumRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable Long id){
        Optional<Album> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Album with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {
        return repo.findAll();
    }

    //CREATE a new album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album newAlbum(@RequestBody Album album){
        return repo.save(album);
    }

    //UPDATE an album
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Album updateAlbum(@RequestBody Album album){
        return repo.save(album);
    }

    //DELETE an album
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id){
        repo.deleteById(id);
    }
}
