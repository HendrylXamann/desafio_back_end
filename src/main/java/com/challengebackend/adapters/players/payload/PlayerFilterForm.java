package com.challengebackend.adapters.players.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerFilterForm {
    @NotNull
    private String playerName;
    private Integer playerAge;
    private String playerCountry;
}
