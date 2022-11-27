package com.felipe.taskmanagementeapi.controllers;

import com.felipe.taskmanagementeapi.dtos.TeamDto;
import com.felipe.taskmanagementeapi.services.impl.TeamServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Employee entity")
@RestController
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @ApiOperation(value = "Create Team")
    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody @Valid TeamDto teamDto){
        TeamDto savedTeamDto = teamService.createTeam(teamDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeamDto);
    }

    @ApiOperation(value = "Find Team by ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamDto> findTeamById(@PathVariable Integer id) {
        TeamDto foundedTeamDto = teamService.findTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundedTeamDto);
    }

    @ApiOperation(value = "Find all Teams")
    @GetMapping
    public ResponseEntity<List<TeamDto>> findAllTeams() {
        List<TeamDto> teamDtoList = teamService.findAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teamDtoList);
    }

    @ApiOperation(value = "Update Name Team")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamDto> updateNameTeam(@RequestBody @Valid TeamDto teamDto, @PathVariable Integer id) {
        TeamDto updatedTeamDto = teamService.updateTeamName(teamDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeamDto);
    }

    @ApiOperation(value = "Delete Team by ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable Integer id) {
        String responseService = teamService.deleteTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}
