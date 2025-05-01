package com.challengebackend.application.players;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.challengebackend.infrastructure.persistence.players.PlayersRepository;

@Service
@RequiredArgsConstructor
public class PlayersServiceImpl implements PlayersService{
    private final PlayersRepository playersRepository;

}
