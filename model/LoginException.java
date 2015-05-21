package model;

public class LoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;

	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
