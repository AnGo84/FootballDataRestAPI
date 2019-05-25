
package ua.footballdata.model;

import java.util.Date;
import java.util.List;

public class Competition {

	private long id;
	private Area area;
	private String name;
	private String code;
	private String emblemUrl;
	private String plan;
	private Season currentSeason;
	private List<Season> seasons = null;
	private Date lastUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
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

	public Season getCurrentSeason() {
		return currentSeason;
	}

	public void setCurrentSeason(Season currentSeason) {
		this.currentSeason = currentSeason;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Competition [id=");
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
		builder.append(", currentSeason=");
		builder.append(currentSeason);
		builder.append(", seasons=");
		builder.append(seasons);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}
