package org.cityfootie.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.FootballMatch;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFootballMatchDto {
    @Positive
    private Integer id;
    @NotBlank
    @JsonProperty("street")
    private String street;
    @NotNull
    @Positive
    @JsonProperty("number_players")
    private int numberPlayers;
    @JsonProperty("date")
    private Timestamp date;

    public static FootballMatch toEntity(UpdateFootballMatchDto dto, int numberMax){
        return new FootballMatch(
                dto.getId(),
                dto.getStreet(),
                numberMax,
                dto.getNumberPlayers(),
                dto.getDate(),
                new HashSet<>()
        );
    }

    public static UpdateFootballMatchDto toDto(FootballMatch footballMatch){
        return new UpdateFootballMatchDto(
                footballMatch.getId(),
                footballMatch.getStreet(),
                footballMatch.getNumberPlayers(),
                footballMatch.getDate()
        );
    }
}
