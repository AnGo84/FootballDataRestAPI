
package ua.footballdata.model.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

//@Entity
//@Table(name = "seasons")

@DynamoDBTable(tableName = "seasons")

/*
 * @JsonRootName("season")
 * 
 * @XmlAccessorType(XmlAccessType.FIELD)
 * 
 * @XmlRootElement(name = "season")
 */
public class SeasonEntity {
	// @Id
	// @Column
	private Long id;
	// @Column(name = "start_date")
	// @Temporal(TemporalType.DATE)
	private String startDate;
	// @Column(name = "end_date")
	// @Temporal(TemporalType.DATE)
	private String endDate;
	// @Column(name = "current_match_day")
	private Integer currentMatchday;
	// @ManyToOne(optional = true)
	// @JoinColumn(name = "winner")
	private TeamEntity winner;

	/*
	 * @XmlElement(name = "matche")
	 * 
	 * @JsonProperty("matche")
	 */
	private List<MatchEntity> matches = null;

	@DynamoDBHashKey(attributeName = "id")
	// @DynamoDBAutoGeneratedKey
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@DynamoDBAttribute
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@DynamoDBAttribute
	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	@DynamoDBAttribute
	@DynamoDBTyped(DynamoDBAttributeType.M)
	public TeamEntity getWinner() {
		return winner;
	}

	public void setWinner(TeamEntity winner) {
		this.winner = winner;
	}

	@DynamoDBAttribute(attributeName = "matches")
	@DynamoDBTyped(DynamoDBAttributeType.L)
	public List<MatchEntity> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchEntity> matches) {
		this.matches = matches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentMatchday == null) ? 0 : currentMatchday.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		SeasonEntity other = (SeasonEntity) obj;
		if (currentMatchday == null) {
			if (other.currentMatchday != null)
				return false;
		} else if (!currentMatchday.equals(other.currentMatchday))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
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
		builder.append("SeasonEntity [id=");
		builder.append(id);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", currentMatchday=");
		builder.append(currentMatchday);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", matches=");
		builder.append(matches);
		builder.append("]");
		return builder.toString();
	}

}
