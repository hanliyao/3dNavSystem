package com.litop.motorroom.litop;

/**
 * Created by litop on 2016/7/22.
 */
public class LitopException extends Exception{

	private static final long serialVersionUID = -6077470673347712421L;

	public LitopException(){}

	public LitopException(String message, Throwable cause){
		super(message, cause);
	}

	public LitopException(String message) {
		super(message);
	}

	public LitopException(Throwable cause) {
		super(cause);
	}
}
