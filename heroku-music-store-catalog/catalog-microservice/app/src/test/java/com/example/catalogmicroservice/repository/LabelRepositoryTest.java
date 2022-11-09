package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelRepositoryTest {
    @Autowired
    private LabelRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteLabel() {
        Label label = new Label();
        label.setName("Cool name");
        label.setWebsite("www.abc.com");

        label = repo.save(label);

        Label whatIGot = repo.findById(label.getLabelId()).get();

        assertEquals(label, whatIGot);

        repo.deleteById(label.getLabelId());

        Optional<Label> shouldBeEmptyOptional = repo.findById(label.getLabelId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}