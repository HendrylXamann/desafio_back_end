package com.challengebackend.infrastructure.persistence.challenges;

import com.challengebackend.domain.challenges.Challenges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengesRepository extends JpaRepository<Challenges, Long> {
}
