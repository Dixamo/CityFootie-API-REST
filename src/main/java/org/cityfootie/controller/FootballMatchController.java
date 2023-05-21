package org.cityfootie.controller;

import org.cityfootie.controller.dto.FootballMatchDto;
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

    @GetMapping(path = "/footballmatches/{street}")
    public ResponseEntity<FootballMatchDto> getFootballMatchByStreet (
            @PathVariable(value = "street") String street
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByStreet(street);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@GetMapping(path = "/footballmatches/{date}")
    public ResponseEntity<FootballMatchDto> getFootballMatchByDate (
            @PathVariable(value = "date") Timestamp date
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByDate(date);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/
}
