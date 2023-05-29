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
public class UpdatePlayerDto {
    @NotBlank
    @JsonProperty("name")
    private String name;
    @NotBlank
    @JsonProperty("surnames")
    private String surnames;
    @NotNull
    @Positive
    @JsonProperty("number")
    private int number;

    public static Player toEntity(UpdatePlayerDto dto, Integer id, String username, String email, String password){
        return new Player(
                id,
                dto.getName(),
                dto.getSurnames(),
                username,
                email,
                password,
                dto.getNumber(),
                new HashSet<>()
        );
    }

    public static UpdatePlayerDto toDto(Player player){
        return new UpdatePlayerDto(
                player.getName(),
                player.getSurnames(),
                player.getNumber()
        );
    }
}
