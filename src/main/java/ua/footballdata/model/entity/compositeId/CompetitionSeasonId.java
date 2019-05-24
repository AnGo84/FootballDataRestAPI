package ua.footballdata.model.entity.compositeId;

import java.io.Serializable;

public class CompetitionSeasonId implements Serializable {
	private static final long serialVersionUID = 1L;

	private long competitionId;
	private long seasonId;

	public CompetitionSeasonId() {
	}

	public CompetitionSeasonId(long competitionId, long seasonId) {
		this.competitionId = competitionId;
		this.seasonId = seasonId;
	}

	public long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(long competitionId) {
		this.competitionId = competitionId;
	}

	public long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(long seasonId) {
		this.seasonId = seasonId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (competitionId ^ (competitionId >>> 32));
		result = prime * result + (int) (seasonId ^ (seasonId >>> 32));
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
		CompetitionSeasonId other = (CompetitionSeasonId) obj;
		if (competitionId != other.competitionId)
			return false;
		if (seasonId != other.seasonId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompetitionSeasonId [competitionId=");
		builder.append(competitionId);
		builder.append(", seasonId=");
		builder.append(seasonId);
		builder.append("]");
		return builder.toString();
	}

}
