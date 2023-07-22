package com.gk.paymt.exception;

public class TranactionNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TranactionNotFound(String m){
		super(m);
	}
}
