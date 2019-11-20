package com.fantasy.football.englishpremierleague.query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fantasy.football.englishpremierleague.api.clients.model.PlayerSummary;
import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceService;
import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceServiceDynamoDB;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;

@Log
public class QueryData implements RequestStreamHandler {
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        log.info("Fetching data");

        JsonObject response = new JsonObject();
        JsonObject header = new JsonObject();
        try {
            LeagueDetailsPersistenceService leagueDetailsPersistenceService =
                    new LeagueDetailsPersistenceServiceDynamoDB();
            Gson gson = new Gson();
            response.add(
                    "body",
                    gson.toJsonTree(leagueDetailsPersistenceService.getAll(),
                            PlayerSummary[].class));
            header.addProperty("statusCode",200);
        }
        catch(Exception e) {
            log.log(Level.SEVERE,e.getMessage(),e);
            header.addProperty("statusCode",500);
            header.addProperty("message",e.getMessage());
        }
        finally {
            response.add("headers",header);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,"UTF-8");
            writer.write(response.toString());
            writer.close();
        }
    }
}
