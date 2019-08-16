package ua.footballdata.model;

import java.util.List;

public class CompetitionTeams {
    private Competition competition;
    private Season season;
    private List<Team> teams = null;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompetitionTeams{");
        sb.append("competition=").append(competition);
        sb.append(", season=").append(season);
        sb.append(", teams=").append(teams);
        sb.append('}');
        return sb.toString();
    }
}
