package ua.footballdata.controller;

public class AppResponse {
	private String message;

	public AppResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppResponse [message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
