package ru.otus.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.otus.domain.User;
import ru.otus.services.DBServiceUser;


import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRestControllerStandaloneTest {

    private MockMvc mvc;

    @Mock
    private DBServiceUser usersService;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserRestController(usersService)).build();
    }

    @Test
    void getUserById() throws Exception {
        User expectedUser = new User(1, "Vasya", "vasia", "password");
        Gson gson = new GsonBuilder().create();

        given(usersService.getById(1L)).willReturn(Optional.of(expectedUser));
        mvc.perform(get("/api/user/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(expectedUser)));
    }
}