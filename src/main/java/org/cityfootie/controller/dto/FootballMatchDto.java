package org.cityfootie.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatchDto {
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    @JsonProperty("street")
    private String street;
    @NotNull
    @Positive
    @JsonProperty("number_max")
    private int numberMax;
    @NotNull
    @Positive
    @JsonProperty("number_players")
    private int numberPlayers;
    @JsonProperty("date")
    private Timestamp date;

    public static FootballMatch toEntity(FootballMatchDto dto){
        return new FootballMatch(
                dto.getId(),
                dto.getStreet(),
                dto.getNumberMax(),
                dto.getNumberPlayers(),
                dto.getDate(),
                new HashSet<>()
        );
    }

    public static FootballMatchDto toDto(FootballMatch footballMatch){
        return new FootballMatchDto(
                footballMatch.getId(),
                footballMatch.getStreet(),
                footballMatch.getNumberMax(),
                footballMatch.getNumberPlayers(),
                footballMatch.getDate()
        );
    }
}

