package ua.footballdata.model;

public class Score {
	private String winner;
	private String duration;
	private MatchScore fullTime;
	private MatchScore halfTime;
	private MatchScore extraTime;
	private MatchScore penalties;

	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public MatchScore getFullTime() {
		return fullTime;
	}
	public void setFullTime(MatchScore fullTime) {
		this.fullTime = fullTime;
	}
	public MatchScore getHalfTime() {
		return halfTime;
	}
	public void setHalfTime(MatchScore halfTime) {
		this.halfTime = halfTime;
	}
	public MatchScore getExtraTime() {
		return extraTime;
	}
	public void setExtraTime(MatchScore extraTime) {
		this.extraTime = extraTime;
	}
	public MatchScore getPenalties() {
		return penalties;
	}
	public void setPenalties(MatchScore penalties) {
		this.penalties = penalties;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [winner=");
		builder.append(winner);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", fullTime=");
		builder.append(fullTime);
		builder.append(", halfTime=");
		builder.append(halfTime);
		builder.append(", extraTime=");
		builder.append(extraTime);
		builder.append(", penalties=");
		builder.append(penalties);
		builder.append("]");
		return builder.toString();
	}
	
	
}
