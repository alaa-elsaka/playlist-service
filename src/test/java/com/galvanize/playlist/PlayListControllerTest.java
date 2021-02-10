package com.galvanize.playlist;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.playlist.model.PlayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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


}
