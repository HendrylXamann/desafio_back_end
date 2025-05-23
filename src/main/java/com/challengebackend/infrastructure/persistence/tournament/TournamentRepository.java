package com.challengebackend.infrastructure.persistence.tournament;

import com.challengebackend.domain.tournament.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    boolean existsByNameIgnoreCase(String name);
}
