package com.challengebackend.infrastructure.persistence.players;

import com.challengebackend.domain.players.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<Players> findByNameContainingIgnoreCase(String name);
}
