package org.cityfootie.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    @JsonProperty("name_player")
    private String namePlayer;
    @NotBlank
    @JsonProperty("first_surname")
    private String firstSurname;
    @NotBlank
    @JsonProperty("second_surname")
    private String secondSurname;
    //@NotBlank
    @JsonProperty("date_birth")
    private Date dateBirth;
    @NotBlank
    @JsonProperty("name_user")
    private String nameUser;
    @NotBlank
    private String email;
    @NotBlank
    @JsonProperty("pass")
    private String password;
    @NotNull
    @Positive
    private int dorsal;
    @NotBlank
    @JsonProperty("favorite_player")
    private String favoritePlayer;
    @NotBlank
    @JsonProperty("favorite_team")
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
                dto.getFavoriteTeam(),
                new HashSet<>()
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
