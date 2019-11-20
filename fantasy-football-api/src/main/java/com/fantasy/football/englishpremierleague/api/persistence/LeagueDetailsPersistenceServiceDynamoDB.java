package com.fantasy.football.englishpremierleague.api.persistence;

import com.amazonaws.services.dynamodbv2.document.*;

import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.fantasy.football.englishpremierleague.api.clients.ClientFactory;
import com.fantasy.football.englishpremierleague.api.clients.model.LeagueSummary;
import com.fantasy.football.englishpremierleague.api.clients.model.PlayerSummary;
import com.fantasy.football.englishpremierleague.api.clients.model.TeamSummary;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class LeagueDetailsPersistenceServiceDynamoDB implements LeagueDetailsPersistenceService {

    private DynamoDB dynamoDB;

    public LeagueDetailsPersistenceServiceDynamoDB() {
        ClientFactory clientFactory = new ClientFactory();
        dynamoDB = clientFactory.createDynamoDbClient();
    }

    @Override
    public boolean saveAll(LeagueSummary leagueSummary) {
        return storeLeagueSummaryDetails(leagueSummary) && storePlayerDetails(leagueSummary);
    }

    @Override
    public PlayerSummary[] getAll() {
        Table table = dynamoDB.getTable("latest-player-data");
        Iterator<Item> iterator = table.scan(new ScanSpec()).iterator();
        Gson gson = new Gson();
        List<PlayerSummary> playerSummaries = new ArrayList<>();

        while (iterator.hasNext()) {
            PlayerSummary playerSummary = gson.fromJson(iterator.next().toJSON(),PlayerSummary.class);
            playerSummaries.add(playerSummary);
        }

        return playerSummaries.toArray(new PlayerSummary[0]);
    }

    private boolean storeLeagueSummaryDetails(LeagueSummary leagueSummary) {
        try {
            Table table = dynamoDB.getTable("fantasy-football-league-data");

            String retrievalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            String dayOfYear = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());

            Function<Map, Map> removeNullValues = map -> {
                Set<String> keys = new HashSet<>(map.keySet());
                for (String key : keys) {
                    Object value = map.get(key);
                    if (value == null || ((value instanceof String) && "".equals(value)))
                        map.remove(key);
                }
                return map;
            };

            Gson gson = new Gson();

            Function<Object, Map> convertObjectToMap = object ->
                    object == null ? null : removeNullValues.apply(gson.fromJson(gson.toJson(object), Map.class));

            Arrays.stream(leagueSummary.elements).parallel().forEach(playerSummary -> {
                TeamSummary teamSummary = null;

                if (playerSummary.team > 0 && playerSummary.team < leagueSummary.teams.length) {
                    teamSummary = leagueSummary.teams[playerSummary.team - 1];
                }

                Item newItem = new Item().withPrimaryKey("retrieval_date_time", retrievalDateTime, "sort_id", playerSummary.id);

                Map<String, Object> playerDetails = convertObjectToMap.apply(playerSummary);
                Map<String, Object> teamDetails = convertObjectToMap.apply(teamSummary);
                if (teamDetails != null && teamDetails.size() > 0)
                    playerDetails.put("teamDetails", teamDetails);
                playerDetails.put("day_of_year", dayOfYear);

                for (Map.Entry<String, Object> keyValue : playerDetails.entrySet()) {
                    newItem.with(keyValue.getKey(), keyValue.getValue());
                }

                System.out.printf("Saving details for %s %s\n", playerSummary.firstName, playerSummary.secondName);
                table.putItem(newItem);
            });

            System.out.println("Finished");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean storePlayerDetails(LeagueSummary leagueSummary) {
        try {
            Table table = dynamoDB.getTable("latest-player-data");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
            LocalDateTime today = LocalDateTime.now();
            String prefix = String.format("%s/%s", dateTimeFormatter.format(today), dateTimeFormatter.format(today.plusYears(1)));
            String dayOfYear = DateTimeFormatter.ofPattern("yyyyMMdd").format(today);

            Gson gson = new Gson();

            Function<Map, Map> removeNullValues = map -> {
                Set<String> keys = new HashSet<>(map.keySet());
                for (String key : keys) {
                    Object value = map.get(key);
                    if (value == null || ((value instanceof String) && "".equals(value)))
                        map.remove(key);
                }
                return map;
            };

            Function<Object, Map> convertObjectToMap = object ->
                    object == null ? null : removeNullValues.apply(gson.fromJson(gson.toJson(object), Map.class));

            Arrays.stream(leagueSummary.elements).parallel().forEach(playerSummary -> {
                String key = String.format("%s_%d", prefix, playerSummary.id);
                Item newItem = new Item().withPrimaryKey("player-season-id", key);

                Map<String, Object> playerDetails = convertObjectToMap.apply(playerSummary);
                playerDetails.put("day_of_year", dayOfYear);

                for (Map.Entry<String, Object> keyValue : playerDetails.entrySet()) {
                    newItem.with(keyValue.getKey(), keyValue.getValue());
                }

                System.out.printf("Saving player details for %s %s\n", playerSummary.firstName, playerSummary.secondName);
                table.putItem(newItem);
            });

            System.out.println("Finished");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean clearAll() {
        try {
            Table table = dynamoDB.getTable("fantasy-football-league-data");
            System.out.println("Clear database...");
            ItemCollection<ScanOutcome> items = table.scan(new ScanSpec());
            List<Item> list = new ArrayList<>();
            Iterator<Item> iterator = items.iterator();
            for (; iterator.hasNext(); )
                list.add(iterator.next());

            list.parallelStream().forEach(item -> {
                PrimaryKey primaryKey = new PrimaryKey("retrieval_date_time", item.get("retrieval_date_time"), "sort_id", item.get("sort_id"));
                System.out.printf("Deleting details for %s %s\n", item.get("first_name"), item.get("second_name"));
                table.deleteItem(primaryKey);
            });
            System.out.println("Finished loading database");

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
