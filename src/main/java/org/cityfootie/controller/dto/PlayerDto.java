package org.cityfootie.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cityfootie.entity.Player;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public static Player toEntity(PlayerDto dto){
        return new Player(
                dto.getId(),
                dto.getEmail(),
                dto.getPassword()
        );
    }

    public static PlayerDto toDto(Player player){
        return new PlayerDto(
                player.getId(),
                player.getEmail(),
                player.getPassword()
        );
    }
}
