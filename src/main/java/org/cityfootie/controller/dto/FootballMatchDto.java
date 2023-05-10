package org.cityfootie.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatchDto {
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    private String calle;
    @NotNull
    @Positive
    private int numberMax;
    @NotNull
    @Positive
    private int numberPlayers;

    public static FootballMatch toEntity(FootballMatchDto dto){
        return new FootballMatch(
                dto.getId(),
                dto.getCalle(),
                dto.getNumberMax(),
                dto.getNumberPlayers()
        );
    }

    public static FootballMatchDto toDto(FootballMatch footballMatch){
        return new FootballMatchDto(
                footballMatch.getId(),
                footballMatch.getCalle(),
                footballMatch.getNumberMax(),
                footballMatch.getNumberPlayers()
        );
    }
}

