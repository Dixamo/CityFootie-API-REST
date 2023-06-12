package org.cityfootie.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    @Positive
    private Integer id;
    @NotBlank
    @JsonProperty("name")
    private String name;
    @NotBlank
    @JsonProperty("surnames")
    private String surnames;
    @NotBlank
    @JsonProperty("username")
    private String username;
    @NotBlank
    @JsonProperty("email")
    private String email;
    @NotBlank
    @JsonProperty("password")
    private String password;
    @NotNull
    @Positive
    @JsonProperty("number")
    private int number;

    public static Player toEntity(PlayerDto dto) {
        return new Player(
                dto.getId(),
                dto.getName(),
                dto.getSurnames(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getNumber(),
                new HashSet<>()
        );
    }

    public static PlayerDto toDto(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getSurnames(),
                player.getUsername(),
                player.getEmail(),
                player.getPassword(),
                player.getNumber()
        );
    }
}
