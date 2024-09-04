package org.bot.oslregistrationbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bot.oslregistrationbackend.entity.Player;
import org.bot.oslregistrationbackend.repository.PlayerRepository;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository repository;

    private final List<Player> playerList = Arrays.asList(
            new Player(1L),
            new Player(2L),
            new Player(3L),
            new Player(4L),
            new Player(5L)
    );

    @BeforeEach
    void setUp() {
        repository.saveAll(playerList);
    }

    @Test
    void findAll() throws Exception {
        String response = mockMvc.perform(get("/player"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        JSONArray jsonResponse = new JSONArray(response);

        assertEquals(playerList.size(), jsonResponse.length());
    }

    @Test
    void findById() throws Exception {
        int index = 0;

        String response = mockMvc.perform(get("/player/" + playerList.get(index).getDiscordId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        String expectedJson = new ObjectMapper().writeValueAsString(playerList.get(index));

        assertEquals(expectedJson, response);
    }

    @Test
    void save() throws Exception {
        Player player = new Player(120923042L);
        String playerJson = new ObjectMapper().writeValueAsString(player);

        mockMvc.perform(post("/player/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk());

        assertTrue(repository.existsById(player.getDiscordId()));
    }

    @Test
    void remove() throws Exception {
        Player player = playerList.get(4);
        String playerJson = new ObjectMapper().writeValueAsString(player);

        mockMvc.perform(delete("/free-agent/" + player.getDiscordId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/player/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk());

        assertFalse(repository.existsById(player.getDiscordId()));
    }

    @Test
    void removeById() throws Exception {
        Player player = playerList.get(4);
        String playerJson = new ObjectMapper().writeValueAsString(player);

        mockMvc.perform(delete("/free-agent/" + player.getDiscordId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/player/delete/" + player.getDiscordId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk());

        assertFalse(repository.existsById(player.getDiscordId()));
    }

    @Test
    void existsById() throws Exception {
        int index = 0;
        String playerJson = new ObjectMapper().writeValueAsString(playerList.get(index));

        String response = mockMvc.perform(get("/player/exists/" + playerList.get(index).getDiscordId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(Boolean.parseBoolean(response));
    }
}