package com.paulo.syservice.service.exption;

public class ObjectNotFaundExption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFaundExption(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFaundExption(String message) {
		super(message);
	}

}
