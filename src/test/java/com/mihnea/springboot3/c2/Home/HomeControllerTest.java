package com.mihnea.springboot3.c2.Home;

import com.mihnea.springboot3.c2.Video.NewVideo;
import com.mihnea.springboot3.c2.Video.VideoService;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean VideoService service;

    @Test
    @WithMockUser
    void indexPageHasSeveralHtmlForms() throws Exception{
        String html = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username: user")))
                .andExpect(content().string(containsString("Authorities: [ROLE_USER]")))
                .andReturn()
                .getResponse().getContentAsString();

        Assertions.assertThat(html).contains(
                "<form action=\"/logout\"",
                "<form action=\"/search\"",
                "<form action=\"/new-video\""
        );
    }

    @Test
    @WithMockUser(roles = "USER", username = "mihnea")
    void postNewVideoTest() throws Exception{
        mockMvc.perform(post("/new-video")
                        .param("name", "new-video")
                        .param("description", "new-description")
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/"));

        verify(service).create(new NewVideo("new-video", "new-description"), "mihnea");
    }



}
