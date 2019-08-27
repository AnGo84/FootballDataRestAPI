package ua.footballdata.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "gamble_rules")
public class GambleRuleEntity {
	private Long id;

	private String fullName;

	private String description;

	private int exactScore;
	private int winnerAndDifferance;
	private int onlyDraw;
	private int onlyWinner;
	private int other;
	private boolean active;

	@DynamoDBHashKey(attributeName = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@DynamoDBAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@DynamoDBAttribute(attributeName = "exact_score")
	public int getExactScore() {
		return exactScore;
	}

	public void setExactScore(int exactScore) {
		this.exactScore = exactScore;
	}

	@DynamoDBAttribute(attributeName = "winner_and_differance")
	public int getWinnerAndDifferance() {
		return winnerAndDifferance;
	}

	public void setWinnerAndDifferance(int winnerAndDifferance) {
		this.winnerAndDifferance = winnerAndDifferance;
	}

	@DynamoDBAttribute(attributeName = "only_draw")
	public int getOnlyDraw() {
		return onlyDraw;
	}

	public void setOnlyDraw(int onlyDraw) {
		this.onlyDraw = onlyDraw;
	}

	@DynamoDBAttribute(attributeName = "only_winner")
	public int getOnlyWinner() {
		return onlyWinner;
	}

	public void setOnlyWinner(int onlyWinner) {
		this.onlyWinner = onlyWinner;
	}

	@DynamoDBAttribute(attributeName = "other")
	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	@DynamoDBAttribute()
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + exactScore;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + onlyDraw;
		result = prime * result + onlyWinner;
		result = prime * result + other;
		result = prime * result + winnerAndDifferance;
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
		GambleRuleEntity other = (GambleRuleEntity) obj;
		if (active != other.active)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (exactScore != other.exactScore)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (onlyDraw != other.onlyDraw)
			return false;
		if (onlyWinner != other.onlyWinner)
			return false;
		if (this.other != other.other)
			return false;
		if (winnerAndDifferance != other.winnerAndDifferance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GambleRule [id=");
		builder.append(id);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", exactScore=");
		builder.append(exactScore);
		builder.append(", winnerAndDifferance=");
		builder.append(winnerAndDifferance);
		builder.append(", onlyDraw=");
		builder.append(onlyDraw);
		builder.append(", onlyWinner=");
		builder.append(onlyWinner);
		builder.append(", other=");
		builder.append(other);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

}
