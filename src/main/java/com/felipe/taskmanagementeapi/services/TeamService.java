package com.felipe.taskmanagementeapi.services;

import com.felipe.taskmanagementeapi.dtos.TeamDto;

public interface TeamService {

    TeamDto createTeam(TeamDto teamDto);
    TeamDto findTeamById(Integer id);
    TeamDto updateTeamName(TeamDto teamDto, Integer id);
}
