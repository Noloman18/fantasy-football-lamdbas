package com.fantasy.football.englishpremierleague.query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceService;
import com.fantasy.football.englishpremierleague.api.persistence.LeagueDetailsPersistenceServiceDynamoDB;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        String response = "Error occurred";
        try {
            LeagueDetailsPersistenceService leagueDetailsPersistenceService =
                    new LeagueDetailsPersistenceServiceDynamoDB();
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            response = mapper.writeValueAsString(
                    leagueDetailsPersistenceService.getAll());
        }
        catch(Exception e) {
            log.log(Level.SEVERE,e.getMessage(),e);
        }
        finally {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,"UTF-8");
            writer.write(response);
            writer.close();
        }
    }
}
