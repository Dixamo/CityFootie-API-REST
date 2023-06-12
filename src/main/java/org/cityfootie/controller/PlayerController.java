package org.cityfootie.controller;

import org.cityfootie.controller.dto.PlayerDto;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;
import org.cityfootie.service.FootballMatchService;
import org.cityfootie.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private FootballMatchService footballMatchService;

    @PostMapping(path = "/players")
    public ResponseEntity<Void> registerPlayer(
            @Valid @RequestBody PlayerDto player
    ) {
        if (playerService.registerPlayer(PlayerDto.toEntity(player))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/players")
    public ResponseEntity<PlayerDto> loginPlayer(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password
    ) {
        Player player = playerService.loginPlayer(email, password);
        if (player != null) {
            return ResponseEntity.ok(PlayerDto.toDto(player));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/players/{playerEmail}")
    public ResponseEntity<PlayerDto> getPlayerByEmail(
            @PathVariable("playerEmail") String playerEmail
    ) {
        Player player = playerService.getPlayerByEmail(playerEmail);
        if (player != null) {
            return ResponseEntity.ok(PlayerDto.toDto(player));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/players/{playerEmail}")
    public ResponseEntity<Void> updatePlayer(
            @PathVariable("playerEmail") String playerEmail,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "surnames", required = true) String surnames,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "number", required = true) Integer number
    ) {
        Player toUpdatePlayer = playerService.getPlayerByEmail(playerEmail);
        if (toUpdatePlayer != null) {
            if (playerService.updatePlayer(toUpdatePlayer, name, surnames, username, number)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/players/{latitude}/{longitude}")
    public ResponseEntity<List<PlayerDto>> getPlayersByFootballMatch(
            @PathVariable(value = "latitude", required = true) double latitude,
            @PathVariable(value = "longitude", required = true) double longitude
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByLatLng(latitude, longitude);
        if (footballMatch.getPlayers() != null) {
            return ResponseEntity.ok(footballMatch.getPlayers().stream().map(PlayerDto::toDto).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}