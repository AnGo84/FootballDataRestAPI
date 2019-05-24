
package ua.footballdata.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seasons")
public class SeasonEntity {
	@Id
	@Column
	private long id;
	@Column
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	private Integer currentMatchday;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private TeamEntity winner;

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

	public TeamEntity getWinner() {
		return winner;
	}

	public void setWinner(TeamEntity winner) {
		this.winner = winner;
	}

}
