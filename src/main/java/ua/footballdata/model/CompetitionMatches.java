package ua.footballdata.model;

import java.util.List;

public class CompetitionMatches {
	private Competition competition;
	private List<Match> matches = null;
	
	public Competition getCompetition() {
		return competition;
	}
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompetitionMatches [competition=");
		builder.append(competition);
		builder.append(", matches=");
		builder.append(matches);
		builder.append("]");
		return builder.toString();
	}
	
	
}
