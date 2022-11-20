package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping(value = "/{id}")
    private ResponseEntity<TeamDto> updateNameTeam(@RequestBody TeamDto teamDto, @PathVariable Integer id) {
        TeamDto updatedTeamDto = teamService.updateTeamName(teamDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeamDto);
    }
}
