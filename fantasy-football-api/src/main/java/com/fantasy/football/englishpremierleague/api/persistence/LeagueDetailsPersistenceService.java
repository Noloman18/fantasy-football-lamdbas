package com.fantasy.football.englishpremierleague.api.persistence;

import com.fantasy.football.englishpremierleague.api.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.api.clients.model.PlayerSummary;

public interface LeagueDetailsPersistenceService {
    boolean saveAll(LeagueSummary leagueSummary);
    PlayerSummary[] getAll();
    boolean clearAll();
}
