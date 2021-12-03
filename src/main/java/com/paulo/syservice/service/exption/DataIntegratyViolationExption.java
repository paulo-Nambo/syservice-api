package com.paulo.syservice.service.exption;

public class DataIntegratyViolationExption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegratyViolationExption(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegratyViolationExption(String message) {
		super(message);
	}

}