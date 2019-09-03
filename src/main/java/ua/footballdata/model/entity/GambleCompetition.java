package ua.footballdata.model.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class GambleCompetition {
	private Long id;
	private Long gambleId;
	private Long seasonId;
	private String name;
	private List<GambleStage> stages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGambleId() {
		return gambleId;
	}

	public void setGambleId(Long gambleId) {
		this.gambleId = gambleId;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GambleStage> getStages() {
		return stages;
	}

	public void setStages(List<GambleStage> stages) {
		this.stages = stages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GambleCompetition [id=");
		builder.append(id);
		builder.append(", gambleId=");
		builder.append(gambleId);
		builder.append(", seasonId=");
		builder.append(seasonId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", stages=");
		builder.append(stages);
		builder.append("]");
		return builder.toString();
	}

}
