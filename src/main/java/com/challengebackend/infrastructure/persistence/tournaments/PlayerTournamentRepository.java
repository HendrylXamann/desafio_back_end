package com.challengebackend.infrastructure.persistence.tournaments;

import com.challengebackend.domain.tournaments.PlayerTournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerTournamentRepository extends JpaRepository<PlayerTournament, Long> {
    List<PlayerTournament> findByTournamentId(Long tournamentId);
}
