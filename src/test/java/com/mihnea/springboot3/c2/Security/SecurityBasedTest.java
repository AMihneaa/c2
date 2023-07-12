package com.mihnea.springboot3.c2.Security;

import com.mihnea.springboot3.c2.Home.HomeController;
import com.mihnea.springboot3.c2.Video.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
@WebMvcTest(controllers = HomeController.class)
public class SecurityBasedTest {

    @Autowired MockMvc mockMvc;
    @MockBean VideoService videoService;

    @Test
    void unauthUserShouldNotAccessHomePage() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "mihnea", roles = "USER")
    void authUserShouldAccessHomePage() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mihnea", roles = "ADMIN")
    void adminShouldAccessHomePage() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void newVideoFromUnauthUserShouldFail() throws Exception{
        mockMvc.perform(post("/new-video").param("name", "new name").param("description", "new descrition").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "mihnea", roles = "USER")
    void newVideoFromUserShouldWork() throws Exception{
        mockMvc.perform(post("/new-video").param("name", "new name").param("description", "new desc").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
