package com.balhau.kobo.exceptions;

@SuppressWarnings("serial")
public class KoboSQLException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9174956396272707095L;

	public KoboSQLException(String message){
		super(message);
	}
	
	public KoboSQLException(Throwable cause){
		super(cause);
	}
	
	public KoboSQLException(String message,Throwable cause){
		super(message,cause);
	}
}
