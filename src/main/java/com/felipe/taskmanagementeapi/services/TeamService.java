package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.TeamDto;

import java.util.List;

public interface TeamService {

    TeamDto createTeam(TeamDto teamDto);
    TeamDto findTeamById(Integer id);
    List<TeamDto> findAllTeams();
    TeamDto updateTeamName(TeamDto teamDto, Integer id);
    String deleteTeamById(Integer id);
}
