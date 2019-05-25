package ua.footballdata.model;

import java.util.List;

public class Match {
	private int id;
	private Season season;
	private String utcDate;
	private String status;
	private String matchday;
	private String stage;
	private String group;
	private String lastUpdated;
	private Score score;
	private MatchTeam homeTeam;
	private MatchTeam awayTeam;
	private List<Referee> referees = null;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public String getUtcDate() {
		return utcDate;
	}
	public void setUtcDate(String utcDate) {
		this.utcDate = utcDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMatchday() {
		return matchday;
	}
	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public MatchTeam getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(MatchTeam homeTeam) {
		this.homeTeam = homeTeam;
	}
	public MatchTeam getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(MatchTeam awayTeam) {
		this.awayTeam = awayTeam;
	}
	public List<Referee> getReferees() {
		return referees;
	}
	public void setReferees(List<Referee> referees) {
		this.referees = referees;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Match [id=");
		builder.append(id);
		builder.append(", season=");
		builder.append(season);
		builder.append(", utcDate=");
		builder.append(utcDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", matchday=");
		builder.append(matchday);
		builder.append(", stage=");
		builder.append(stage);
		builder.append(", group=");
		builder.append(group);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append(", score=");
		builder.append(score);
		builder.append(", homeTeam=");
		builder.append(homeTeam);
		builder.append(", awayTeam=");
		builder.append(awayTeam);
		builder.append(", referees=");
		builder.append(referees);
		builder.append("]");
		return builder.toString();
	}

	

}
