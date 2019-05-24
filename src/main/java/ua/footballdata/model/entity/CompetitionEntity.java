
package ua.footballdata.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column
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
	@Column
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

}
