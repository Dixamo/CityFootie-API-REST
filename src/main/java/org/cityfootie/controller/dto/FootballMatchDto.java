package org.cityfootie.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.FootballMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatchDto {
    @NotNull
    @Positive
    private Integer id;

    @NotBlank
    private String street;

    @NotNull
    @Positive
    private int numberMax;

    @NotNull
    @Positive
    private int numberPlayers;

    public static FootballMatch toEntity(FootballMatchDto dto){
        return new FootballMatch(
                dto.getId(),
                dto.getStreet(),
                dto.getNumberMax(),
                dto.getNumberPlayers(),
                new HashSet<>()
        );
    }

    public static FootballMatchDto toDto(FootballMatch footballMatch){
        return new FootballMatchDto(
                footballMatch.getId(),
                footballMatch.getStreet(),
                footballMatch.getNumberMax(),
                footballMatch.getNumberPlayers()
        );
    }
}

