package ua.footballdata.model.entity;

public class GambleUserScore {
	private Long gambleId;
	private Long userId;
	private GambleUser user;
	private Integer score;

	public GambleUserScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GambleUserScore(Long gambleId, GambleUser user, Integer score) {
		super();
		this.gambleId = gambleId;
		this.userId = user.getId();
		this.user = user;
		this.score = score;
	}

	public Long getGambleId() {
		return gambleId;
	}

	public void setGambleId(Long gambleId) {
		this.gambleId = gambleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public GambleUser getUser() {
		return user;
	}

	public void setUser(GambleUser user) {
		this.user = user;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gambleId == null) ? 0 : gambleId.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		GambleUserScore other = (GambleUserScore) obj;
		if (gambleId == null) {
			if (other.gambleId != null)
				return false;
		} else if (!gambleId.equals(other.gambleId))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GambleScoreTable [gambleId=");
		builder.append(gambleId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", user=");
		builder.append(user);
		builder.append(", score=");
		builder.append(score);
		builder.append("]");
		return builder.toString();
	}

}
