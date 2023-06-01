package org.cityfootie.controller;

import org.cityfootie.controller.dto.FootballMatchDto;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;
import org.cityfootie.service.FootballMatchService;
import org.cityfootie.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
public class FootballMatchController {
    @Autowired
    private FootballMatchService footballMatchService;
    @Autowired
    private PlayerService playerService;

    @GetMapping(path = "/footballMatches")
    public ResponseEntity<FootballMatchDto> getFootbalMatch(
            @RequestParam(value = "latitude", required = true) double latitude,
            @RequestParam(value = "longitude", required = true) double longitude
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByLatLng(latitude, longitude);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/footballMatches")
    public ResponseEntity<Void> createFootballMatch(
            @Valid @RequestBody FootballMatchDto footballMatchDto
    ) {
        if (footballMatchService.createFootballMatch(FootballMatchDto.toEntity(footballMatchDto))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/footballMatches")
    public ResponseEntity<Void> joinPlayerToFootbalMatch(
            @RequestParam(value = "email", required = true) String  playerEmail,
            @RequestParam(value = "latitude", required = true) double latitude,
            @RequestParam(value = "longitude", required = true) double longitude
    ) {
        Player player = playerService.getPlayerByEmail(playerEmail);
        FootballMatch footballMatch = footballMatchService.getFootballMatchByLatLng(latitude, longitude);
        if (footballMatchService.joinPlayerToFootballMatch(player, footballMatch)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }









    /*@GetMapping(path = "/footballMatches")
    public ResponseEntity<List<FootballMatchDto>> getAllFootballMatches() {
        return ResponseEntity.ok(
                footballMatchService
                        .getAllFootballmatches()
                        .stream()
                        .map(FootballMatchDto::toDto)
                        .collect(Collectors.toList())
        );
    }*/

    /*@GetMapping(path = "/footballMatchesByStreet")
    public ResponseEntity<FootballMatchDto> getFootballMatchByStreet (
            @RequestParam(value = "street", required = true) String street
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByStreet(street);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/

    @GetMapping(path = "/footballMatchesByDate")
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
    /*@PutMapping("/footballMatches/{footballMatchId}")
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
    }*/
}
