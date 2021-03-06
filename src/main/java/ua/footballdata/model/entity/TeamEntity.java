
package ua.footballdata.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

//@Entity
//@Table(name = "teams")
@DynamoDBTable(tableName = "teams")
public class TeamEntity {
	// @Id
	// @Column
	private long id;
	// @ManyToOne
	// @JoinColumn(name = "area_id")
	private AreaEntity area;
	// @Column
	private String name;
	// @Column(name = "short_name")
	private String shortName;
	// @Column(name = "tla")
	private String tla;
	// @Column(name = "crest_url")
	private String crestUrl;
	// @Column(name = "address")
	private String address;
	// @Column(name = "phone")
	private String phone;
	// @Column(name = "website")
	private String website;
	// @Column
	private String email;
	// @Column(name = "founded")
	private String founded;
	// @Column(name = "club_colors")
	private String clubColors;
	// @Column
	private String venue;
	// @Column(name = "last_updated")
	// @Temporal(TemporalType.DATE)
	private String lastUpdated;

	@DynamoDBHashKey(attributeName = "id")
	// @DynamoDBAutoGeneratedKey
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@DynamoDBAttribute
	@DynamoDBTyped(DynamoDBAttributeType.M)
	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	@DynamoDBAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@DynamoDBAttribute
	public String getTla() {
		return tla;
	}

	public void setTla(String tla) {
		this.tla = tla;
	}

	@DynamoDBAttribute
	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}

	@DynamoDBAttribute
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@DynamoDBAttribute
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@DynamoDBAttribute
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@DynamoDBAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBAttribute
	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	@DynamoDBAttribute
	public String getClubColors() {
		return clubColors;
	}

	public void setClubColors(String clubColors) {
		this.clubColors = clubColors;
	}

	@DynamoDBAttribute
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	@DynamoDBAttribute
	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((clubColors == null) ? 0 : clubColors.hashCode());
		result = prime * result + ((crestUrl == null) ? 0 : crestUrl.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((founded == null) ? 0 : founded.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((tla == null) ? 0 : tla.hashCode());
		result = prime * result + ((venue == null) ? 0 : venue.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		TeamEntity other = (TeamEntity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (clubColors == null) {
			if (other.clubColors != null)
				return false;
		} else if (!clubColors.equals(other.clubColors))
			return false;
		if (crestUrl == null) {
			if (other.crestUrl != null)
				return false;
		} else if (!crestUrl.equals(other.crestUrl))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (founded == null) {
			if (other.founded != null)
				return false;
		} else if (!founded.equals(other.founded))
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (tla == null) {
			if (other.tla != null)
				return false;
		} else if (!tla.equals(other.tla))
			return false;
		if (venue == null) {
			if (other.venue != null)
				return false;
		} else if (!venue.equals(other.venue))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeamEntity [id=");
		builder.append(id);
		builder.append(", area=");
		builder.append(area);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", tla=");
		builder.append(tla);
		builder.append(", crestUrl=");
		builder.append(crestUrl);
		builder.append(", address=");
		builder.append(address);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", website=");
		builder.append(website);
		builder.append(", email=");
		builder.append(email);
		builder.append(", founded=");
		builder.append(founded);
		builder.append(", clubColors=");
		builder.append(clubColors);
		builder.append(", venue=");
		builder.append(venue);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append("]");
		return builder.toString();
	}

}
