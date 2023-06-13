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

@RestController
public class FootballMatchController {
    @Autowired
    private FootballMatchService footballMatchService;
    @Autowired
    private PlayerService playerService;

    /**
     * Endpoint encargado de obtener el partido creado en cierta pista (buscándolo por latitud y longitud)
     *
     * @param latitude
     * @param longitude
     * @return ResponseEntity<FootballMatchDto>
     */
    @GetMapping(path = "/footballMatches/{latitude}/{longitude}")
    public ResponseEntity<FootballMatchDto> getFootbalMatch(
            @PathVariable(value = "latitude", required = true) double latitude,
            @PathVariable(value = "longitude", required = true) double longitude
    ) {
        FootballMatch footballMatch = footballMatchService.getFootballMatchByLatLng(latitude, longitude);
        if (footballMatch != null) {
            return ResponseEntity.ok(FootballMatchDto.toDto(footballMatch));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint encargado de crear un partido nuevo.
     *
     * @param footballMatchDto
     * @return ResponseEntity<Void>
     */
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

    /**
     * Endpoint encargado de vincular a un jugador con un partido ya creado.
     *
     * @param latitude
     * @param longitude
     * @param playerEmail
     * @return ResponseEntity<Void>
     */
    @PutMapping("/footballMatches/{latitude}/{longitude}")
    public ResponseEntity<Void> joinPlayerToFootbalMatch(
            @PathVariable(value = "latitude", required = true) double latitude,
            @PathVariable(value = "longitude", required = true) double longitude,
            @RequestParam(value = "email", required = true) String playerEmail
    ) {
        Player player = playerService.getPlayerByEmail(playerEmail);
        FootballMatch footballMatch = footballMatchService.getFootballMatchByLatLng(latitude, longitude);
        if (player == null || footballMatch == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            if (footballMatchService.joinPlayerToFootballMatch(player, footballMatch)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
    }
}
