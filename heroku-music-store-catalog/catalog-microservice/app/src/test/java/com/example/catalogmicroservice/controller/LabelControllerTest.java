package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.model.Label;
import com.example.catalogmicroservice.repository.LabelRepository;
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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private LabelRepository repo;
    private ObjectMapper mapper = new ObjectMapper();

    Label inputLabel;
    Label outputLabel;
    List<Label> allLabels = new ArrayList<>();

    @Before
    public void setup(){
        inputLabel = new Label();
        inputLabel.setName("Cool Label");
        inputLabel.setWebsite("www.coolwebsite.com");

        outputLabel = new Label();
        outputLabel.setLabelId(1L);
        outputLabel.setName("Cool label");
        outputLabel.setWebsite("www.coolwebsite.com");

        allLabels = new ArrayList<>(Arrays.asList(
                outputLabel
        ));

    }
    //GET all Labels
    @Test
    public void shouldReturnAllLabelOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allLabels);

        doReturn(allLabels).
                when // conditional
                (repo).findAll(); //method we want to test

        mockMvc.perform(
                        get("/label"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }
    //GET label by id
    @Test
    public void shouldReturnLabelById() throws Exception{
        String outputJson = mapper.writeValueAsString(outputLabel);

        doReturn(Optional.of(outputLabel)).when(repo).findById(1L);

        mockMvc.perform(
                        get("/label/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));

    }
    //CREATE an label
    @Test
    public void shouldCreateNewLabelOnPostRequest() throws Exception {

        String inputLabelJson = mapper.writeValueAsString(inputLabel);
        String outputLabelJson = mapper.writeValueAsString(outputLabel);

        doReturn(outputLabel).when(repo).save(inputLabel);

        // Act
        mockMvc.perform(post("/label")
                        .content(inputLabelJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelJson));
    }
    //    Update/PUT a label
    @Test
    public void shouldUpdateLabelByIdAndReturn204StatusCode() throws Exception {
        inputLabel.setLabelId(1L);
        inputLabel.setName("Even Cooler Name");
        String inputJson = mapper.writeValueAsString(inputLabel);

        doReturn(Optional.of(outputLabel)).when(repo).findById(1L);

        mockMvc.perform(
                        put("/label")
//                                .andDo(print()
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //   Delete a label
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/label/1")).andExpect(status().isNoContent());
    }

}