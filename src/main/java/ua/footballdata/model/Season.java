
package ua.footballdata.model;

public class Season {

	private long id;
	/*
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale =
	 * "en_GB") private Date startDate;
	 */
	private String startDate;
	/*
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale =
	 * "en_GB") private Date endDate;
	 */
	private String endDate;
	private Integer currentMatchday;
	private Team winner;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Season [id=");
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
		builder.append("]");
		return builder.toString();
	}

}
