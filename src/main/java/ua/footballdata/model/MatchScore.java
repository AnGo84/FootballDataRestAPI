package ua.footballdata.model;

public class MatchScore {
	private Integer homeTeam;
	private Integer awayTeam;

	public Integer getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Integer homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Integer getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Integer awayTeam) {
		this.awayTeam = awayTeam;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchTime [homeTeam=");
		builder.append(homeTeam);
		builder.append(", awayTeam=");
		builder.append(awayTeam);
		builder.append("]");
		return builder.toString();
	}

}
