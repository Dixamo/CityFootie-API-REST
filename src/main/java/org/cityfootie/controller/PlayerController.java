package org.cityfootie.controller;

import org.cityfootie.controller.dto.PlayerDto;
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

    @GetMapping(path = "/players")
    public ResponseEntity<PlayerDto> loginPlayer(
            @RequestParam(value = "email", required = false) String  email,
            @RequestParam(value = "password", required = false) String  password) {
        Player player = playerService.loginPlayer(email, password);
        if (player != null) {
            return ResponseEntity.ok(PlayerDto.toDto(player));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

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

    /*@GetMapping(path = "/players")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(
                playerService
                        .getAllPlayers()
                        .stream()
                        .map(PlayerDto::toDto)
                        .collect(Collectors.toList())
        );
    }*/
}
