package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping
    private ResponseEntity<TeamDto> createTeam(@RequestBody @Valid TeamDto teamDto){
        TeamDto savedTeamDto = teamService.createTeam(teamDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeamDto);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<TeamDto> getTeamById(@PathVariable Integer id) {
        TeamDto foundedTeamDto = teamService.findTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundedTeamDto);
    }

    @GetMapping
    private ResponseEntity<List<TeamDto>> findAllTeams() {
        List<TeamDto> teamDtoList = teamService.findAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teamDtoList);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<TeamDto> updateNameTeam(@RequestBody TeamDto teamDto, @PathVariable Integer id) {
        TeamDto updatedTeamDto = teamService.updateTeamName(teamDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeamDto);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<String> deleteTeamById(@PathVariable Integer id) {
        String responseService = teamService.deleteTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}
