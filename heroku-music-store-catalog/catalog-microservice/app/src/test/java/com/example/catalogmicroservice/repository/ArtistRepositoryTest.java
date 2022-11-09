package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Album;
import com.example.catalogmicroservice.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteArtist() {
        Artist artist = new Artist();
        artist.setName("Cool name");
        artist.setInstagram("Insta");
        artist.setTwitter("Twitt");

        artist = repo.save(artist);

        Artist whatIGot = repo.findById(artist.getArtistId()).get();

        assertEquals(artist, whatIGot);

        repo.deleteById(artist.getArtistId());

        Optional<Artist> shouldBeEmptyOptional = repo.findById(artist.getArtistId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}