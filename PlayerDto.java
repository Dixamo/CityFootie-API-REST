package org.cityfootie.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    private String namePlayer;
    @NotBlank
    private String firstSurname;
    @NotBlank
    private String secondSurname;
    @NotBlank
    private Date dateBirth;
    @NotBlank
    private String nameUser;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    @Positive
    private int dorsal;
    @NotBlank
    private String favoritePlayer;
    @NotBlank
    private String favoriteTeam;

    public static Player toEntity(PlayerDto dto){
        return new Player(
                dto.getId(),
                dto.getNamePlayer(),
                dto.getFirstSurname(),
                dto.getSecondSurname(),
                dto.getDateBirth(),
                dto.getNameUser(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getDorsal(),
                dto.getFavoritePlayer(),
                dto.getFavoriteTeam()
        );
    }

    public static PlayerDto toDto(Player player){
        return new PlayerDto(
                player.getId(),
                player.getNamePlayer(),
                player.getFirstSurname(),
                player.getSecondSurname(),
                player.getDateBirth(),
                player.getNameUser(),
                player.getEmail(),
                player.getPassword(),
                player.getDorsal(),
                player.getFavoritePlayer(),
                player.getFavoriteTeam()
        );
    }
}
