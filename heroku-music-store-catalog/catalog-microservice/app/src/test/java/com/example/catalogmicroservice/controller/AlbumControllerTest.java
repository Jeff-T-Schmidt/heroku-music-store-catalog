package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Album;
import com.example.catalogmicroservice.repository.AlbumRepository;
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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AlbumRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    Album inputAlbum;
    Album outputAlbum;
    List<Album> allAlbums = new ArrayList<>();

    @Before
    public void setup(){
        inputAlbum = new Album();
        inputAlbum.setTitle("Cool title");
        inputAlbum.setArtistId(1L);
        inputAlbum.setLabelId(1L);
        inputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022-11-07));
        inputAlbum.setListPrice(25.00);

        outputAlbum = new Album();
        outputAlbum.setAlbumId(1L);
        outputAlbum.setTitle("Cool title");
        outputAlbum.setArtistId(1L);
        outputAlbum.setLabelId(1L);
        outputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022-11-07));
        outputAlbum.setListPrice(25.00);

        allAlbums = new ArrayList<>(Arrays.asList(
                outputAlbum
        ));

    }
    //GET all albums
    @Test
    public void shouldReturnAllAlbumOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allAlbums);

        doReturn(allAlbums).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/album"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET album by id
    @Test
    public void shouldReturnAlbumById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/album/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an album
    @Test
    public void shouldCreateNewAlbumOnPostRequest() throws Exception {

        String inputAlbumJson = mapper.writeValueAsString(inputAlbum);
        String outputAlbumJson = mapper.writeValueAsString(outputAlbum);

        doReturn(outputAlbum).when(repo).save(inputAlbum);

        // Act
        mockMvc.perform(post("/album")
                        .content(inputAlbumJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));
    }
    //    Update/PUT a album
    @Test
    public void shouldUpdateAlbumByIdAndReturn204StatusCode() throws Exception {
        inputAlbum.setAlbumId(1L);
        inputAlbum.setTitle("Even Cooler Title");
        String inputJson = mapper.writeValueAsString(inputAlbum);

        doReturn(Optional.of(outputAlbum)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/album")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a album
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/album/1")).andExpect(status().isNoContent());
    }

}