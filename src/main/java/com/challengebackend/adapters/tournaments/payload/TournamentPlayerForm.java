package com.challengebackend.adapters.tournaments.payload;

import jakarta.validation.constraints.NotNull;

public record TournamentPlayerForm(@NotNull Long playerId) {
}
