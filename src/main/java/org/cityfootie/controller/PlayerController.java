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

    /**
     * Endpoint encargado de registrar a un nuevo usuario.
     *
     * @param player
     * @return ResponseEntity<Void>
     */
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

    /**
     * Endpoint encargado de iniciar sesión con el email y la contraseña del usuario registrado en la BBDD.
     *
     * @param email
     * @param password
     * @return ResponseEntity<PlayerDto>
     */
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

    /**
     * Endpoint encargado de obtener el usuario buscándolo a través del email
     * (para no tener la contraseña circulando por distintas pantallas y asi tener mayor seguridad).
     *
     * @param playerEmail
     * @return ResponseEntity<PlayerDto>
     */
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

    /**
     * Endpoint encargado de modificar los datos del usuario
     * (salvo la contraseña, ya que habría que seguir otro proceso para cambiarla con seguridad).
     *
     * @param playerEmail
     * @param name
     * @param surnames
     * @param username
     * @param number
     * @return ResponseEntity<Void>
     */
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

    /**
     * Endpoint encargado de obtener los jugadores apuntados a un partido.
     *
     * @param latitude
     * @param longitude
     * @return ResponseEntity<List < PlayerDto>>
     */
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