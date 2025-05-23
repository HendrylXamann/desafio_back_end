CREATE TABLE challenge_score (
     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
     player_tournament_id BIGINT NOT NULL,
     challenge_type VARCHAR(50) NOT NULL,
     score INT NOT NULL,
     FOREIGN KEY (player_tournament_id) REFERENCES player_tournament(id) ON DELETE CASCADE
);
