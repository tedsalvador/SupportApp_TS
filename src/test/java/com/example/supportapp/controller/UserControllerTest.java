package com.example.supportapp.controller;

import com.example.supportapp.model.User;
import com.example.supportapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/* import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; */
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = new User();
        user.setIdUser(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setUserPassword("password123");
        user.setUserRole("USER");
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"username\":\"johndoe\",\"userPassword\":\"password123\",\"userRole\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("johndoe"))));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("johndoe"))));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is(equalTo("johndoe"))));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setIdUser(1L);
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Doe");
        updatedUser.setUsername("janedoe");
        updatedUser.setUserPassword("password456");
        updatedUser.setUserRole("USER");

        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/{id}", 1L)
                .contentType("application/json")
                .content("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"userPassword\":\"password456\",\"userRole\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("janedoe"))));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        when(userService.authenticateUser("johndoe", "password123")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/users/login")
                .param("username", "johndoe")
                .param("userPassword", "password123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("johndoe"))));
    }
}
