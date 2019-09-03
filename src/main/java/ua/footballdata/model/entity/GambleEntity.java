package ua.footballdata.model.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "gambles")
public class GambleEntity {
	private Long id;
	private String name;
	private String description;
	private Boolean active;
	private List<GambleCompetition> competitions;
	private List<GambleUser> participants;
	private GambleRuleEntity rule;

	@DynamoDBHashKey(attributeName = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@DynamoDBAttribute
	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@DynamoDBAttribute(attributeName = "competitions")
	@DynamoDBTyped(DynamoDBAttributeType.L)
	public List<GambleCompetition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<GambleCompetition> competitons) {
		this.competitions = competitons;
	}

	@DynamoDBAttribute(attributeName = "users")
	@DynamoDBTyped(DynamoDBAttributeType.L)
	public List<GambleUser> getParticipants() {
		return participants;
	}

	public void setParticipants(List<GambleUser> participants) {
		this.participants = participants;
	}

	@DynamoDBAttribute(attributeName = "rule")
	@DynamoDBTyped(DynamoDBAttributeType.M)
	public GambleRuleEntity getRule() {
		return rule;
	}

	public void setRule(GambleRuleEntity rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gamble [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", active=");
		builder.append(active);
		builder.append(", competitions=");
		builder.append(competitions);
		builder.append(", participants=");
		builder.append(participants);
		builder.append(", rule=");
		builder.append(rule);
		builder.append("]");
		return builder.toString();
	}

}
