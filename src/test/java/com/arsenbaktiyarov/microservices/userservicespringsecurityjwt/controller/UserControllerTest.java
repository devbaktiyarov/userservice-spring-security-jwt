package com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.controller;

import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.contoller.UserController;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.domain.User;
import com.arsenbaktiyarov.microservices.userservicespringsecurityjwt.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user")
@TestPropertySource("/application-test.properties")
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    ArrayList<User> users;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        users.add(new User(1L, "user", "user", "pass", null));
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void getUsers() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/")).andExpect(authenticated());
        when(userService.getUsers()).thenReturn(users);
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void saveEntity() throws Exception {
        this.mockMvc.perform(post("/api/v1/users/")
                .contentType(APPLICATION_JSON)).andExpect(status().isForbidden());
        this.mockMvc.perform(post("/api/v1/users/role/")
                .contentType(APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    void updateToken() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/token/refresh"))
                .andExpect(status().isOk());
    }


}
