CREATE TABLE challenges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    weight INT NOT NULL
);

CREATE TABLE players (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL UNIQUE
     age INT NOT NULL,
     country VARCHAR(255) NOT NULL
);

CREATE TABLE tournaments (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     date DATE NOT NULL,
     is_finalized BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE player_tournament (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       player_id BIGINT NOT NULL,
       tournament_id BIGINT NOT NULL,
       score INT NOT NULL,
       FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
       FOREIGN KEY (tournament_id) REFERENCES tournaments(id) ON DELETE CASCADE
);