package com.fantasy.football.englishpremierleague.api.clients.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamSummary {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("draw")
    @Expose
    public Integer draw;
    @SerializedName("form")
    @Expose
    public Object form;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("loss")
    @Expose
    public Integer loss;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("played")
    @Expose
    public Integer played;
    @SerializedName("points")
    @Expose
    public Integer points;
    @SerializedName("position")
    @Expose
    public Integer position;
    @SerializedName("short_name")
    @Expose
    public String shortName;
    @SerializedName("strength")
    @Expose
    public Integer strength;
    @SerializedName("team_division")
    @Expose
    public Object teamDivision;
    @SerializedName("unavailable")
    @Expose
    public Boolean unavailable;
    @SerializedName("win")
    @Expose
    public Integer win;
    @SerializedName("strength_overall_home")
    @Expose
    public Integer strengthOverallHome;
    @SerializedName("strength_overall_away")
    @Expose
    public Integer strengthOverallAway;
    @SerializedName("strength_attack_home")
    @Expose
    public Integer strengthAttackHome;
    @SerializedName("strength_attack_away")
    @Expose
    public Integer strengthAttackAway;
    @SerializedName("strength_defence_home")
    @Expose
    public Integer strengthDefenceHome;
    @SerializedName("strength_defence_away")
    @Expose
    public Integer strengthDefenceAway;

}