package org.cityfootie.controller;

import org.cityfootie.controller.dto.PlayerDto;
import org.cityfootie.controller.dto.UpdatePlayerDto;
import org.cityfootie.entity.Player;
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
            @RequestParam(value = "email", required = true) String  email,
            @RequestParam(value = "password", required = true) String  password
    ) {
        Player player = playerService.loginPlayer(email, password);
        if (player != null) {
            return ResponseEntity.ok(PlayerDto.toDto(player));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/players/{playerId}")
    public ResponseEntity<Void> updatePlayer(
            @PathVariable("playerId") Integer playerId,
            @Valid @RequestBody UpdatePlayerDto player
    ) {
        Player toUpdatePlayer = playerService.getPlayerById(playerId);
        if (toUpdatePlayer != null) {
            if (playerService.updatePlayer(playerId, UpdatePlayerDto.toEntity(player, toUpdatePlayer.getEmail(), toUpdatePlayer.getPassword()))) {
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

    @GetMapping(path = "/allPlayers")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(
                playerService
                        .getAllPlayers()
                        .stream()
                        .map(PlayerDto::toDto)
                        .collect(Collectors.toList())
        );
    }
    @GetMapping(path = "/playersByNumber")
    public ResponseEntity<PlayerDto> getByNumber(
            @RequestParam(value = "number", required = true) int  number){
        Player player = playerService.getByNumber(number);
        if (player != null) {
            return ResponseEntity.ok(PlayerDto.toDto(player));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}