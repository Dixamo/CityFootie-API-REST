package org.cityfootie.controller;

import org.cityfootie.controller.dto.FootballMatchDto;
import org.cityfootie.controller.dto.UpdateFootballMatchDto;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.service.FootballMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FootballMatchController {
    @Autowired
    private FootballMatchService footballMatchService;

    @PostMapping(path = "/footballmatches")
    public ResponseEntity<Void> createFootballMatch(
            @Valid @RequestBody FootballMatchDto footballMatchDto
    ) {
        if (footballMatchService.createFootballMatch(FootballMatchDto.toEntity(footballMatchDto))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/footballmatches")
    public ResponseEntity<List<FootballMatchDto>> getAllFootballmatches() {
        return ResponseEntity.ok(
                footballMatchService
                        .getAllFootballmatches()
                        .stream()
                        .map(FootballMatchDto::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "/footballmatchesByStreet")
    public ResponseEntity<FootballMatchDto> getFootballMatchByStreet (
            @RequestParam(value = "street", required = true) String street
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByStreet(street);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/footballmatchesByDate")
    public ResponseEntity<FootballMatchDto> getFootballMatchByDate (
            @RequestParam(value = "date", required = true) Timestamp date
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByDate(date);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/footballmatches/{footballMatchId}")
    public ResponseEntity<Void> updateFootballmatch(
            @PathVariable("footballMatchId") Integer footballMatchId,
            @Valid @RequestBody UpdateFootballMatchDto footballMatch
    ) {
        FootballMatch toUpdateFootballMatch = footballMatchService.getFootballMatchById(footballMatchId);
        if (toUpdateFootballMatch != null) {
            if (footballMatchService.updateFootballMatch(footballMatchId, UpdateFootballMatchDto.toEntity(footballMatch, toUpdateFootballMatch.getNumberMax()))) {
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
