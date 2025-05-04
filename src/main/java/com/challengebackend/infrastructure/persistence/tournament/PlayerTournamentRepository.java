package com.challengebackend.infrastructure.persistence.tournament;

import com.challengebackend.domain.tournament.playertournment.PlayerTournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerTournamentRepository extends JpaRepository<PlayerTournament, Long> {
    List<PlayerTournament> findByTournamentId(Long tournamentId);

    Optional<PlayerTournament> findByPlayer_id(Long playerTournamentId);
}
