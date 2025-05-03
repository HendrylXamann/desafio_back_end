CREATE TABLE challenge_score (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     player_tournament_id BIGINT NOT NULL,
     challenge_type VARCHAR(50) NOT NULL,
     score INT NOT NULL,
     FOREIGN KEY (player_tournament_id) REFERENCES player_tournament(id) ON DELETE CASCADE
);
