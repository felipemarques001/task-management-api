package com.felipe.taskmanagementeapi.services.impl;

import com.felipe.taskmanagementeapi.Repositories.TeamRepository;
import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.entities.TeamEntity;
import com.felipe.taskmanagementeapi.exceptions.ResourceNotFoundException;
import com.felipe.taskmanagementeapi.services.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(savedTeam.isEmpty()) {
            throw new ResourceNotFoundException("Team", "id", id);
        }

        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(savedTeam.get(), teamDto);
        return teamDto;
    }

    @Override
    public List<TeamDto> findAllTeams() {
        List<TeamEntity> savedTeamList = teamRepository.findAll();
        List<TeamDto> teamDtoList = savedTeamList.stream().map(TeamDto::new).toList();
        return teamDtoList;
    }

    @Override
    public TeamDto updateTeamName(TeamDto teamDto, Integer id) {
        Optional<TeamEntity> savedTeam = teamRepository.findById(id);
        if(savedTeam.isEmpty()) {
            throw new ResourceNotFoundException("Team", "id", id);
        }

        savedTeam.get().setName(teamDto.getName());
        teamRepository.save(savedTeam.get());
        BeanUtils.copyProperties(savedTeam.get(), teamDto);
        return teamDto;
    }

    @Override
    public String deleteTeamById(Integer id) {
        Optional<TeamEntity> savedTeam = teamRepository.findById(id);
        if(savedTeam.isEmpty()) {
            throw new ResourceNotFoundException("Team", "id", id);
        }
        teamRepository.deleteById(id);
        return "Team successfully deleted!";
    }
}
