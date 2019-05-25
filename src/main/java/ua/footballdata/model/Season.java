
package ua.footballdata.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Season {

    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "en_GB")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "en_GB")
    private Date endDate;
    private Integer currentMatchday;
    private Team winner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
		builder.append("]");
		return builder.toString();
	}

    

}
