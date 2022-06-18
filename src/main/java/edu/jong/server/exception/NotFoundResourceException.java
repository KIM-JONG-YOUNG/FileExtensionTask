package edu.jong.server.exception;

public class NotFoundResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundResourceException(String message) {
		super(message);
	}
}
