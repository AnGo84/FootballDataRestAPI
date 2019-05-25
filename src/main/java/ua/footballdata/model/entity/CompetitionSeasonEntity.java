package ua.footballdata.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ua.footballdata.model.entity.compositeId.CompetitionSeasonId;

@Entity
@Table(name = "competitions_seasons")
@IdClass(CompetitionSeasonId.class)

public class CompetitionSeasonEntity {
	@Id
	private long competitionId;
	@Id
	private long seasonId;

	public CompetitionSeasonEntity() {
	}

	public CompetitionSeasonEntity(long competitionId, long seasonId) {
		super();
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
		CompetitionSeasonEntity other = (CompetitionSeasonEntity) obj;
		if (competitionId != other.competitionId)
			return false;
		if (seasonId != other.seasonId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompetitionSeasonEntity [competitionId=");
		builder.append(competitionId);
		builder.append(", seasonId=");
		builder.append(seasonId);
		builder.append("]");
		return builder.toString();
	}

}
