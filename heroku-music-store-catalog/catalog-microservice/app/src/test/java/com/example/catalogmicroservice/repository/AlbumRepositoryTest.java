package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Album;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRepositoryTest {
    @Autowired
    private AlbumRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteAlbum() {
        Album album = new Album();
        album.setTitle("Cool Title");
        album.setArtistId(1L);
        album.setReleaseDate(LocalDate.ofEpochDay(2022-11-07));
        album.setLabelId(1L);
        album.setListPrice(25.00);

        album = repo.save(album);

        Album whatIGot = repo.findById(album.getAlbumId()).get();

        assertEquals(album, whatIGot);

        repo.deleteById(album.getAlbumId());

        Optional<Album> shouldBeEmptyOptional = repo.findById(album.getAlbumId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }

}