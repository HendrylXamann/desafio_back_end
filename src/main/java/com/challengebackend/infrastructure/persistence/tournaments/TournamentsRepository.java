package com.challengebackend.infrastructure.persistence.tournaments;

import com.challengebackend.domain.tournaments.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentsRepository extends JpaRepository<Tournaments, Long> {
    boolean existsByNameIgnoreCase(String name);
}
