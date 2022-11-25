package com.felipe.taskmanagementeapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TeamControllerITest {

    private static final String TEAM_NAME = "nameTest";
    private static final String TEAM_NEW_NAME = "newNameTest";
    private static final String NAME_ERROR_MESSAGE = "Name cannot be empty!";
    private static final Integer INVALID_ID = 1;
    private static final String ID_ERROR_MESSAGE = "Team not found with id : " + INVALID_ID;
    private static final String ERROR_DETAILS_URL = "uri=/team/" + INVALID_ID;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        teamRepository.deleteAll();
    }

    @DisplayName("Integration test for createTeam method - success case")
    @Test
    void givenTeamDto_whenCreateTeam_thenReturnSavedTeam() throws Exception {
        // given
        TeamDto teamDto = new TeamDto();
        teamDto.setName(TEAM_NAME);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", CoreMatchers.is(TEAM_NAME)));
    }

    @DisplayName("Integration test for createTeam method - fail case")
    @Test
    void givenInvalidName_whenCreateTeam_thenBadRequestAndErrorDetails() throws Exception {
        // given
        TeamDto teamDto = new TeamDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", CoreMatchers.is(NAME_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for findTeamById method - success case")
    @Test
    void givenValidId_whenFindTeamById_thenReturnSavedTeam() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/team/{id}", teamEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{'id': " + teamEntity.getId() + ",name:'" + TEAM_NAME + "',tasks:[], employees:[]}"));
    }

    @DisplayName("Integration test for findTeamById method - fail case")
    @Test
    void givenInvalidId_whenFindTeamById_thenReturnNotFoundAndErrorDetails() throws Exception {
        // given
        // Nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/team/{id}", INVALID_ID));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(ID_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL)));
    }

    @DisplayName("Integration test for findAllTeams method")
    @Test
    void givenSavedTeam_whenFindAllTeams_thenReturnTeamDtoList() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/team"));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id': " + teamEntity.getId() + ",name:'" + TEAM_NAME + "',tasks:[], employees:[]}]"));
    }

    @DisplayName("Integration test for updateNameTeam method - success case")
    @Test
    void givenNewName_whenUpdateNameTeam_thenReturnUpdatedTeam() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TeamDto teamDto = new TeamDto();
        teamDto.setName(TEAM_NEW_NAME);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/team/{id}", teamEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{'id': " + teamEntity.getId() + ",name:'" + TEAM_NEW_NAME + "',tasks:[], employees:[]}"));
    }

    @DisplayName("Integration test for updateNameTeam method - fail case - invalid name")
    @Test
    void givenInvalidName_whenUpdateNameTeam_thenReturnBadRequestAndErrorDetails() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        TeamDto teamDto = new TeamDto();

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/team/{id}", teamEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", CoreMatchers.is(NAME_ERROR_MESSAGE)));
    }

    @DisplayName("Integration test for updateNameTeam method - fail case - invalid id")
    @Test
    void givenInvalidId_whenUpdateNameTeam_thenReturnResourceNotFoundAndErrorDetails() throws Exception {
        // given
        TeamDto teamDto = new TeamDto();
        teamDto.setName(TEAM_NEW_NAME);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/team/{id}", INVALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(ID_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL)));
    }

    @DisplayName("Integration test for deleteTeamById method - success case")
    @Test
    void givenValidId_whenDeleteTeamById_thenReturnOkAndSuccessString() throws Exception {
        // given
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(TEAM_NAME);
        teamRepository.save(teamEntity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/team/{id}", teamEntity.getId()));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Integration test for deleteTeamById method - fail case")
    @Test
    void givenInvalidId_whenDeleteTeamById_thenReturnResourceNotFoundAndErrorDetails() throws Exception {
        // given
        // nothing

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/team/{id}", INVALID_ID));

        // then
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(ID_ERROR_MESSAGE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", CoreMatchers.is(ERROR_DETAILS_URL)));
    }
}
