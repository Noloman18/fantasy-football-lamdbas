package com.fantasy.football.englishpremierleague.api.utils;

import com.fantasy.football.englishpremierleague.api.clients.model.LeagueSummary;

public interface ImageDownloader {
    void downloadAndSaveImages(LeagueSummary leagueSummary);
}
