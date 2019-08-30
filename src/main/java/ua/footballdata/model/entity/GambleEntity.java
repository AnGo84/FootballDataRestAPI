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
	private boolean active;
	private List<GambleCompetition> competitons;
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
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@DynamoDBAttribute(attributeName = "competitions")
	public List<GambleCompetition> getCompetitons() {
		return competitons;
	}

	public void setCompetitons(List<GambleCompetition> competitons) {
		this.competitons = competitons;
	}

	@DynamoDBAttribute(attributeName = "users")
	public List<GambleUser> getParticipants() {
		return participants;
	}

	public void setParticipants(List<GambleUser> participants) {
		this.participants = participants;
	}

	@DynamoDBAttribute
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
		builder.append(", competitons=");
		builder.append(competitons);
		builder.append(", participants=");
		builder.append(participants);
		builder.append(", rule=");
		builder.append(rule);
		builder.append("]");
		return builder.toString();
	}

}
