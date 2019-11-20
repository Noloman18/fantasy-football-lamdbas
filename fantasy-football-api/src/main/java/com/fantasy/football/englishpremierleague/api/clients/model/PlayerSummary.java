package com.fantasy.football.englishpremierleague.api.clients.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerSummary {
    @SerializedName("chance_of_playing_next_round")
    @Expose
    public Integer chanceOfPlayingNextRound;
    @SerializedName("chance_of_playing_this_round")
    @Expose
    public Object chanceOfPlayingThisRound;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("cost_change_event")
    @Expose
    public Integer costChangeEvent;
    @SerializedName("cost_change_event_fall")
    @Expose
    public Integer costChangeEventFall;
    @SerializedName("cost_change_start")
    @Expose
    public Integer costChangeStart;
    @SerializedName("cost_change_start_fall")
    @Expose
    public Integer costChangeStartFall;
    @SerializedName("dreamteam_count")
    @Expose
    public Integer dreamteamCount;
    @SerializedName("element_type")
    @Expose
    public Integer elementType;
    @SerializedName("ep_next")
    @Expose
    public Object epNext;
    @SerializedName("ep_this")
    @Expose
    public Object epThis;
    @SerializedName("event_points")
    @Expose
    public Integer eventPoints;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("form")
    @Expose
    public String form;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("in_dreamteam")
    @Expose
    public Boolean inDreamteam;
    @SerializedName("news")
    @Expose
    public String news;
    @SerializedName("news_added")
    @Expose
    public String newsAdded;
    @SerializedName("now_cost")
    @Expose
    public Integer nowCost;
    @SerializedName("photo")
    @Expose
    public String photo;//http://platform-static-files.s3.amazonaws.com/premierleague/photos/players/110x140/p103955.png
    @SerializedName("points_per_game")
    @Expose
    public String pointsPerGame;
    @SerializedName("second_name")
    @Expose
    public String secondName;
    @SerializedName("selected_by_percent")
    @Expose
    public String selectedByPercent;
    @SerializedName("special")
    @Expose
    public Boolean special;
    @SerializedName("squad_number")
    @Expose
    public Object squadNumber;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("team")
    @Expose
    public Integer team;
    @SerializedName("team_code")
    @Expose
    public Integer teamCode;
    @SerializedName("total_points")
    @Expose
    public Integer totalPoints;
    @SerializedName("transfers_in")
    @Expose
    public Integer transfersIn;
    @SerializedName("transfers_in_event")
    @Expose
    public Integer transfersInEvent;
    @SerializedName("transfers_out")
    @Expose
    public Integer transfersOut;
    @SerializedName("transfers_out_event")
    @Expose
    public Integer transfersOutEvent;
    @SerializedName("value_form")
    @Expose
    public String valueForm;
    @SerializedName("value_season")
    @Expose
    public String valueSeason;
    @SerializedName("web_name")
    @Expose
    public String webName;
    @SerializedName("minutes")
    @Expose
    public Integer minutes;
    @SerializedName("goals_scored")
    @Expose
    public Integer goalsScored;
    @SerializedName("assists")
    @Expose
    public Integer assists;
    @SerializedName("clean_sheets")
    @Expose
    public Integer cleanSheets;
    @SerializedName("goals_conceded")
    @Expose
    public Integer goalsConceded;
    @SerializedName("own_goals")
    @Expose
    public Integer ownGoals;
    @SerializedName("penalties_saved")
    @Expose
    public Integer penaltiesSaved;
    @SerializedName("penalties_missed")
    @Expose
    public Integer penaltiesMissed;
    @SerializedName("yellow_cards")
    @Expose
    public Integer yellowCards;
    @SerializedName("red_cards")
    @Expose
    public Integer redCards;
    @SerializedName("saves")
    @Expose
    public Integer saves;
    @SerializedName("bonus")
    @Expose
    public Integer bonus;
    @SerializedName("bps")
    @Expose
    public Integer bps;
    @SerializedName("influence")
    @Expose
    public String influence;
    @SerializedName("creativity")
    @Expose
    public String creativity;
    @SerializedName("threat")
    @Expose
    public String threat;
    @SerializedName("ict_index")
    @Expose
    public String ictIndex;
}