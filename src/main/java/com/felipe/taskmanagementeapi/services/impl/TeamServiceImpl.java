package com.felipe.taskmanagementeapi.services.impl;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.services.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        TeamEntity teamEntity = new TeamEntity();
        BeanUtils.copyProperties(teamDto, teamEntity);
        teamRepository.save(teamEntity);
        teamDto.setId(teamEntity.getId());
        return teamDto;
    }

    @Override
    public TeamDto findTeamById(Integer id) {
        Optional<TeamEntity> savedTeam = teamRepository.findById(id);
        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(savedTeam.get(), teamDto);
        return teamDto;
    }

    @Override
    public TeamDto updateTeamName(TeamDto teamDto, Integer id) {
        Optional<TeamEntity> savedTeam = teamRepository.findById(id);
        savedTeam.get().setName(teamDto.getName());
        teamRepository.save(savedTeam.get());
        BeanUtils.copyProperties(savedTeam.get(), teamDto);
        return teamDto;
    }
}
