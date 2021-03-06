
package ua.footballdata.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Area {

	private long id;
	private String name;
	private String countryCode;
	private String ensignUrl;
	private long parentAreaId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", locale = "en_GB")
	private Date lastUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEnsignUrl() {
		return ensignUrl;
	}

	public void setEnsignUrl(String ensignUrl) {
		this.ensignUrl = ensignUrl;
	}

	public long getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(long parentAreaId) {
		this.parentAreaId = parentAreaId;
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
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((ensignUrl == null) ? 0 : ensignUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (parentAreaId ^ (parentAreaId >>> 32));
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
		Area other = (Area) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (ensignUrl == null) {
			if (other.ensignUrl != null)
				return false;
		} else if (!ensignUrl.equals(other.ensignUrl))
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
		if (parentAreaId != other.parentAreaId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Area [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", ensignUrl=");
		builder.append(ensignUrl);
		builder.append(", parentAreaId=");
		builder.append(parentAreaId);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}
