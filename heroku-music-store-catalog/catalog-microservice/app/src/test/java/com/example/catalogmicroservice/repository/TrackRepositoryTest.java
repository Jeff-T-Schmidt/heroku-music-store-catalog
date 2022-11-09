package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Track;
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
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteTrack() {
        Track track = new Track();
        track.setAlbumId(1L);
        track.setTitle("Cool Title");
        track.setRunTime(50L);

        track = repo.save(track);

        Track whatIGot = repo.findById(track.getTrackId()).get();

        assertEquals(track, whatIGot);

        repo.deleteById(track.getTrackId());

        Optional<Track> shouldBeEmptyOptional = repo.findById(track.getTrackId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }

}