package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Track;
import com.example.catalogmicroservice.repository.TrackRepository;
import com.example.catalogmicroservice.repository.TrackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TrackRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    Track inputTrack;
    Track outputTrack;
    List<Track> allTracks = new ArrayList<>();

    @Before
    public void setup(){
        inputTrack = new Track();
        inputTrack.setTitle("Cool track title");
        inputTrack.setRunTime(90L);
        inputTrack.setAlbumId(1L);

        outputTrack = new Track();
        outputTrack.setTitle("Cool track title");
        outputTrack.setRunTime(90L);
        outputTrack.setAlbumId(1L);
        outputTrack.setTrackId(1L);


        allTracks = new ArrayList<>(Arrays.asList(
                outputTrack
        ));

    }
    //GET all Tracks
    @Test
    public void shouldReturnAllTrackOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allTracks);

        doReturn(allTracks).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/track"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET track by id
    @Test
    public void shouldReturnTrackById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputTrack);

        doReturn(Optional.of(outputTrack)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/track/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE a track
    @Test
    public void shouldCreateNewTrackOnPostRequest() throws Exception {

        String inputTrackJson = mapper.writeValueAsString(inputTrack);
        String outputTrackJson = mapper.writeValueAsString(outputTrack);

        doReturn(outputTrack).when(repo).save(inputTrack);

        // Act
        mockMvc.perform(post("/track")
                        .content(inputTrackJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackJson));
    }
    //    Update/PUT a track
    @Test
    public void shouldUpdateTrackByIdAndReturn204StatusCode() throws Exception {
        inputTrack.setTrackId(1L);
        inputTrack.setTitle("Cooler track title");
        String inputJson = mapper.writeValueAsString(inputTrack);

        doReturn(Optional.of(outputTrack)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/track")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a track
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/track/1")).andExpect(status().isNoContent());
    }

}