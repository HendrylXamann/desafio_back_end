package com.challengebackend.application.tournaments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentsServiceImpl implements TournamentsService {
    private final TournamentsRepository tournamentsRepository;
}
