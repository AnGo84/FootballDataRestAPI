package ua.footballdata.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matches")
public class MatchEntity {
	@Id
	@Column
	private long id;
	@ManyToOne
	@JoinColumn(name = "season_id")
	private SeasonEntity season;
	@Column(name = "[utc_date]")
	@Temporal(TemporalType.DATE)
	private Date utcDate;
	@Column(name = "[status]")
	private String status;
	@Column(name = "match_day")
	private String matchday;
	@Column(name = "[stage]")
	private String stage;
	@Column(name = "[group]")
	// @Column(name="\"group\"")
	// @Column(name="[group]")
	private String group;
	@ManyToOne
	@JoinColumn(name = "home_team_id")
	private TeamEntity homeTeam;
	@ManyToOne
	@JoinColumn(name = "away_team_id")
	private TeamEntity awayTeam;
	@Column(name = "winner")
	private String winner;
	@Column(name = "[duration]")
	private String duration;
	@Column(name = "score_full_time_home_team")
	private Integer scoreFullTimeHomeTeam;

	@Column(name = "score_full_time_away_team")
	private Integer scoreFullTimeAwayTeam;

	@Column(name = "score_extra_time_home_team")
	private Integer scoreExtraTimeHomeTeam;

	@Column(name = "score_extra_time_away_team")
	private Integer scoreExtraTimeAwayTeam;

	@Column(name = "score_penalties_home_team")
	private Integer scorePenaltiesHomeTeam;

	@Column(name = "score_penalties_away_team")
	private Integer scorePenaltiesAwayTeam;

	@Column(name = "last_updated")
	@Temporal(TemporalType.DATE)
	private Date lastUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SeasonEntity getSeason() {
		return season;
	}

	public void setSeason(SeasonEntity season) {
		this.season = season;
	}

	public Date getUtcDate() {
		return utcDate;
	}

	public void setUtcDate(Date utcDate) {
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

	public TeamEntity getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamEntity homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamEntity getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamEntity awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getScoreFullTimeHomeTeam() {
		return scoreFullTimeHomeTeam;
	}

	public void setScoreFullTimeHomeTeam(Integer scoreFullTimeHomeTeam) {
		this.scoreFullTimeHomeTeam = scoreFullTimeHomeTeam;
	}

	public Integer getScoreFullTimeAwayTeam() {
		return scoreFullTimeAwayTeam;
	}

	public void setScoreFullTimeAwayTeam(Integer scoreFullTimeAwayTeam) {
		this.scoreFullTimeAwayTeam = scoreFullTimeAwayTeam;
	}

	public Integer getScoreExtraTimeHomeTeam() {
		return scoreExtraTimeHomeTeam;
	}

	public void setScoreExtraTimeHomeTeam(Integer scoreExtraTimeHomeTeam) {
		this.scoreExtraTimeHomeTeam = scoreExtraTimeHomeTeam;
	}

	public Integer getScoreExtraTimeAwayTeam() {
		return scoreExtraTimeAwayTeam;
	}

	public void setScoreExtraTimeAwayTeam(Integer scoreExtraTimeAwayTeam) {
		this.scoreExtraTimeAwayTeam = scoreExtraTimeAwayTeam;
	}

	public Integer getScorePenaltiesHomeTeam() {
		return scorePenaltiesHomeTeam;
	}

	public void setScorePenaltiesHomeTeam(Integer scorePenaltiesHomeTeam) {
		this.scorePenaltiesHomeTeam = scorePenaltiesHomeTeam;
	}

	public Integer getScorePenaltiesAwayTeam() {
		return scorePenaltiesAwayTeam;
	}

	public void setScorePenaltiesAwayTeam(Integer scorePenaltiesAwayTeam) {
		this.scorePenaltiesAwayTeam = scorePenaltiesAwayTeam;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((matchday == null) ? 0 : matchday.hashCode());
		result = prime * result + ((scoreExtraTimeAwayTeam == null) ? 0 : scoreExtraTimeAwayTeam.hashCode());
		result = prime * result + ((scoreExtraTimeHomeTeam == null) ? 0 : scoreExtraTimeHomeTeam.hashCode());
		result = prime * result + ((scoreFullTimeAwayTeam == null) ? 0 : scoreFullTimeAwayTeam.hashCode());
		result = prime * result + ((scoreFullTimeHomeTeam == null) ? 0 : scoreFullTimeHomeTeam.hashCode());
		result = prime * result + ((scorePenaltiesAwayTeam == null) ? 0 : scorePenaltiesAwayTeam.hashCode());
		result = prime * result + ((scorePenaltiesHomeTeam == null) ? 0 : scorePenaltiesHomeTeam.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((utcDate == null) ? 0 : utcDate.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchEntity other = (MatchEntity) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (matchday == null) {
			if (other.matchday != null)
				return false;
		} else if (!matchday.equals(other.matchday))
			return false;
		if (scoreExtraTimeAwayTeam == null) {
			if (other.scoreExtraTimeAwayTeam != null)
				return false;
		} else if (!scoreExtraTimeAwayTeam.equals(other.scoreExtraTimeAwayTeam))
			return false;
		if (scoreExtraTimeHomeTeam == null) {
			if (other.scoreExtraTimeHomeTeam != null)
				return false;
		} else if (!scoreExtraTimeHomeTeam.equals(other.scoreExtraTimeHomeTeam))
			return false;
		if (scoreFullTimeAwayTeam == null) {
			if (other.scoreFullTimeAwayTeam != null)
				return false;
		} else if (!scoreFullTimeAwayTeam.equals(other.scoreFullTimeAwayTeam))
			return false;
		if (scoreFullTimeHomeTeam == null) {
			if (other.scoreFullTimeHomeTeam != null)
				return false;
		} else if (!scoreFullTimeHomeTeam.equals(other.scoreFullTimeHomeTeam))
			return false;
		if (scorePenaltiesAwayTeam == null) {
			if (other.scorePenaltiesAwayTeam != null)
				return false;
		} else if (!scorePenaltiesAwayTeam.equals(other.scorePenaltiesAwayTeam))
			return false;
		if (scorePenaltiesHomeTeam == null) {
			if (other.scorePenaltiesHomeTeam != null)
				return false;
		} else if (!scorePenaltiesHomeTeam.equals(other.scorePenaltiesHomeTeam))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (utcDate == null) {
			if (other.utcDate != null)
				return false;
		} else if (!utcDate.equals(other.utcDate))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchEntity [id=");
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
		builder.append(", homeTeam=");
		builder.append(homeTeam);
		builder.append(", awayTeam=");
		builder.append(awayTeam);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", scoreFullTimeHomeTeam=");
		builder.append(scoreFullTimeHomeTeam);
		builder.append(", scoreFullTimeAwayTeam=");
		builder.append(scoreFullTimeAwayTeam);
		builder.append(", scoreExtraTimeHomeTeam=");
		builder.append(scoreExtraTimeHomeTeam);
		builder.append(", scoreExtraTimeAwayTeam=");
		builder.append(scoreExtraTimeAwayTeam);
		builder.append(", scorePenaltiesHomeTeam=");
		builder.append(scorePenaltiesHomeTeam);
		builder.append(", scorePenaltiesAwayTeam=");
		builder.append(scorePenaltiesAwayTeam);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}