package com.galvanize.playlist;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.playlist.DBUitil.PlayListExistException;
import com.galvanize.playlist.DBUitil.PlayListNameException;
import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.repositories.PlayListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PlayListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private PlayListRepository playListRepository;

    @BeforeEach
    public void setup(){
        playListRepository.deleteAll();

        PlayList playList1 = new PlayList("playlist 1");
        PlayList playList2 = new PlayList("playlist 2");
        PlayList playList3 = new PlayList("playlist 3");
        PlayList playList4 = new PlayList("playlist 4");

        playListRepository.save(playList1);
        playListRepository.save(playList2);
        playListRepository.save(playList3);
        playListRepository.save(playList4);
    }

    @Test
    public void whenCreateAnewPlayListDoesNotExist() throws Exception {
        PlayList playList = new PlayList("First Play List");

        mockMvc
                .perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playList)))
                        .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("First Play List"))
                .andDo(document("AddPlaylist", requestFields(
                        fieldWithPath("name").description("The name of the playlist"),
                        fieldWithPath("id").ignored()
                )))
                .andDo(document("AddPlaylist", responseFields(
                        fieldWithPath("name").description("The name of the playlist"),
                        fieldWithPath("id").ignored()
                )));

    }



    @Test
    public void whenCreateAnewPlayListAlreadyExist() throws Exception {
        PlayList playList = new PlayList("playlist 2");

        mockMvc
                .perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playList)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                instanceof PlayListExistException))
                .andExpect(result -> assertEquals("Playlist with this name already exist",
                        result.getResolvedException().getMessage()))
                .andDo(document("AddPlaylist-alreadyExist", requestFields(
                        fieldWithPath("name").description("The name of the playlist"),
                        fieldWithPath("id").ignored()
                )));

    }

    @Test
    public void whenCreateAnewPlayListNoName() throws Exception {
        PlayList playList = new PlayList();

        mockMvc
                .perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playList)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof PlayListNameException))
                .andExpect(result -> assertEquals("Playlist Name Required!",
                        result.getResolvedException().getMessage()))
                .andDo(document("AddPlaylist-NoName", requestFields(
                        fieldWithPath("name").ignored(),
                        fieldWithPath("id").ignored()
                )));

    }

}
