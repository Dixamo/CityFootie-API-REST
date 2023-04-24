package org.cityfootie.controller;

import org.cityfootie.controller.dto.FootballMatchDto;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FootballMatchController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(path = "/footballmatches")
    public ResponseEntity<List<FootballMatchDto>> getAllFootballmatches() {
        return ResponseEntity.ok(
                playerService
                        .getAllFootballmatches()
                        .stream()
                        .map(FootballMatchDto::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "/footballmatches/{footballMatchId}")
    public ResponseEntity<FootballMatchDto> getFlight(
            @PathVariable("footballMatchId") String footballMatchId
    ) {
        FootballMatch footballMatch = playerService.getFootballMatch(footballMatchId);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {
            //  ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
