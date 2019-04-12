package ua.footballdata.model;

public class Referee {
	private int id;
	private String name;
	private Object nationality;

	public int getId() {
	return id;
	}

	public void setId(int id) {
	this.id = id;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public Object getNationality() {
	return nationality;
	}

	public void setNationality(Object nationality) {
	this.nationality = nationality;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Referee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nationality=");
		builder.append(nationality);
		builder.append("]");
		return builder.toString();
	}

	
}
