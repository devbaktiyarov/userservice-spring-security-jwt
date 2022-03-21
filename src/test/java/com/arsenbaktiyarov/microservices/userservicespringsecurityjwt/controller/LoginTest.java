package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.controller;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.contoller.UserController;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class LoginTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("tony").password("spring"))
                .andExpect(status().isOk());
    }

    @Test
    void badCredentials() throws Exception {
        this.mockMvc.perform(post("/api/login")
                .param("username", "bad")
                .param("password", ""))
                .andExpect(status().is4xxClientError());
    }





}
