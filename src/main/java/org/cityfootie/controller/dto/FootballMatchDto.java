package org.cityfootie.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.FootballMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.HashSet;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatchDto {
    @Positive
    private Integer id;
    @NotNull
    @JsonProperty("latitude")
    private double latitude;
    @NotNull
    @JsonProperty("longitude")
    private double longitude;
    @NotNull
    @Positive
    @JsonProperty("number_max")
    private int numberMax;
    @NotNull
    @JsonProperty("number_players")
    private int numberPlayers;
    @JsonProperty("date")
    private Timestamp date;


    public static FootballMatch toEntity(FootballMatchDto dto) {
        return new FootballMatch(
                dto.getId(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getNumberMax(),
                dto.getNumberPlayers(),
                dto.getDate(),
                new HashSet<>()
        );
    }

    public static FootballMatchDto toDto(FootballMatch footballMatch) {
        return new FootballMatchDto(
                footballMatch.getId(),
                footballMatch.getLatitude(),
                footballMatch.getLongitude(),
                footballMatch.getNumberMax(),
                footballMatch.getNumberPlayers(),
                footballMatch.getDate()
        );
    }
}

