package com.fantasy.football.englishpremierleague;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fantasy.football.englishpremierleague.api.clients.ClientFactory;
import com.fantasy.football.englishpremierleague.api.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.api.datasource.FantasyFootballDataSource;
import com.fantasy.football.englishpremierleague.api.notifications.Notifier;
import com.fantasy.football.englishpremierleague.api.notifications.NotifierAwsSns;
import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceService;
import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceServiceDynamoDB;
import lombok.extern.java.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log
public class RetrievalTask implements RequestHandler {

    @Override
    public Object handleRequest(Object o, Context context) {
        Notifier notifier = new NotifierAwsSns();

        try {
            ClientFactory clientFactory = new ClientFactory();

            FantasyFootballDataSource fantasyFootballDataSource =
                    clientFactory.createFantasyFootballDataSource();

            LeagueDetailsPersistenceService leagueDetailsPersistenceService =
                    new LeagueDetailsPersistenceServiceDynamoDB();

            LeagueSummary leagueSummary = fantasyFootballDataSource.retrieveLeagueDetails();
            leagueDetailsPersistenceService.saveAll(leagueSummary);
            notifier.sendNotification(
                    String.format("Notification time %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                    "Successfully retrieved fantasy football data");
        } catch (Exception e) {
            e.printStackTrace();
            notifier.sendNotification(e);
        }
        return "yes";
    }
}
