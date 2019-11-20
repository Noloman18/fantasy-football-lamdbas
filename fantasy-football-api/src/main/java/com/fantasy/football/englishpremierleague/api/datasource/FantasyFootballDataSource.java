package com.fantasy.football.englishpremierleague.api.datasource;

import com.fantasy.football.englishpremierleague.api.clients.model.LeagueSummary;

public interface FantasyFootballDataSource {
    LeagueSummary retrieveLeagueDetails();
}
