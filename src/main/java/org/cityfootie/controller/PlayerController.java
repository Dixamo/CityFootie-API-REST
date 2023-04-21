package org.cityfootie.controller;

import org.cityfootie.controller.dto.PlayerDto;
import org.cityfootie.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(path = "/players")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(
                playerService
                        .getAllPlayers()
                        .stream()
                        .map(PlayerDto::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "/players")
    public ResponseEntity<List<PlayerDto>> getPlayerLogin() {
        return ResponseEntity.ok(
                playerService
                        .getAllPlayers()
                        .stream()
                        .map(PlayerDto::toDto)
                        .collect(Collectors.toList())
        );
    }
}
