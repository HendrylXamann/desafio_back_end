package com.challengebackend.adapters.players.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerForm {
    @NotNull
    private String playerName;
    @NotNull
    private Integer playerAge;
    @NotNull
    private String playerCountry;
}
