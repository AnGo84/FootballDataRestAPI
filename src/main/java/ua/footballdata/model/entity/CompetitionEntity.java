
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
@Table(name = "competitions")
public class CompetitionEntity {
	@Id
	@Column
	private long id;
	@ManyToOne
	@JoinColumn(name = "area_id")
	private AreaEntity area;
	@Column
	private String name;
	@Column
	private String code;
	@Column(name = "emblem_url")
	private String emblemUrl;
	@Column
	private String plan;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "season_id") private SeasonEntity currentSeason;
	 * 
	 * @Column private List<SeasonEntity> seasons = null;
	 */
	@Column(name = "last_updated")
	@Temporal(TemporalType.DATE)
	private Date lastUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmblemUrl() {
		return emblemUrl;
	}

	public void setEmblemUrl(String emblemUrl) {
		this.emblemUrl = emblemUrl;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	/*
	 * public SeasonEntity getCurrentSeason() { return currentSeason; }
	 * 
	 * public void setCurrentSeason(SeasonEntity currentSeason) { this.currentSeason
	 * = currentSeason; }
	 * 
	 * public List<SeasonEntity> getSeasons() { return seasons; }
	 * 
	 * public void setSeasons(List<SeasonEntity> seasons) { this.seasons = seasons;
	 * }
	 */

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
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((emblemUrl == null) ? 0 : emblemUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
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
		CompetitionEntity other = (CompetitionEntity) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (emblemUrl == null) {
			if (other.emblemUrl != null)
				return false;
		} else if (!emblemUrl.equals(other.emblemUrl))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompetitionEntity [id=");
		builder.append(id);
		builder.append(", area=");
		builder.append(area);
		builder.append(", name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append(", emblemUrl=");
		builder.append(emblemUrl);
		builder.append(", plan=");
		builder.append(plan);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}
