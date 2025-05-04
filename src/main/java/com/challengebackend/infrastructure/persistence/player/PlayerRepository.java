package com.challengebackend.infrastructure.persistence.player;

import com.challengebackend.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<Player> findByNameContainingIgnoreCase(String name);
}
