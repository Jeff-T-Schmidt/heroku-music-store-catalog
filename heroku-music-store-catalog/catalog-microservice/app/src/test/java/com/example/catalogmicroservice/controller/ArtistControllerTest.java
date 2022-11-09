package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Artist;
import com.example.catalogmicroservice.repository.ArtistRepository;
import com.example.catalogmicroservice.repository.ArtistRepository;
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

import java.time.LocalDate;
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
@WebMvcTest(ArtistController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ArtistRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    Artist inputArtist;
    Artist outputArtist;
    List<Artist> allArtists = new ArrayList<>();

    @Before
    public void setup(){
        inputArtist = new Artist();
        inputArtist.setName("Cool artist");
        inputArtist.setInstagram("insta");
        inputArtist.setTwitter("Twitt");

        outputArtist = new Artist();
        outputArtist.setArtistId(1L);
        outputArtist.setName("Cool artist");
        outputArtist.setInstagram("insta");
        outputArtist.setTwitter("Twitt");

        allArtists = new ArrayList<>(Arrays.asList(
                outputArtist
        ));

    }
    //GET all artists
    @Test
    public void shouldReturnAllArtistOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allArtists);

        doReturn(allArtists).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/artist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET artist by id
    @Test
    public void shouldReturnArtistById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputArtist);

        doReturn(Optional.of(outputArtist)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/artist/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an artist
    @Test
    public void shouldCreateNewArtistOnPostRequest() throws Exception {

        String inputArtistJson = mapper.writeValueAsString(inputArtist);
        String outputArtistJson = mapper.writeValueAsString(outputArtist);

        doReturn(outputArtist).when(repo).save(inputArtist);

        // Act
        mockMvc.perform(post("/artist")
                        .content(inputArtistJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistJson));
    }
    //    Update/PUT a artist
    @Test
    public void shouldUpdateArtistByIdAndReturn204StatusCode() throws Exception {
        inputArtist.setArtistId(1L);
        inputArtist.setName("Even Cooler Name");
        String inputJson = mapper.writeValueAsString(inputArtist);

        doReturn(Optional.of(outputArtist)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/artist")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a artist
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/artist/1")).andExpect(status().isNoContent());
    }

}