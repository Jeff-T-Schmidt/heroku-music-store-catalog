package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Label;
import com.example.catalogmicroservice.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value ="/label")
public class LabelController {
    @Autowired
    private LabelRepository repo;

    //GET by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabelById(@PathVariable Long id){
        Optional<Label> returnVal = repo.findById(id);
        if(returnVal.isPresent() == false){
            throw new IllegalArgumentException("No Label with that id"+id);
        }
        return returnVal.get();
    }
    //GET all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() {
        return repo.findAll();
    }

    //CREATE a new label
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Label newLabel(@RequestBody Label label){
        return repo.save(label);
    }

    //UPDATE an label
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Label updateLabel(@RequestBody Label label){
        return repo.save(label);
    }

    //DELETE an label
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Long id){
        repo.deleteById(id);
    }
}
