package com.notoon.idc.exceptions;

/**
 * Exception personnalisée
 * 
 * @author t_dc
 */
public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 3676043814281907790L;

	/**
	 * Crée une nouvelle instance de NombreNonValideException
	 */
	public InvalidInputException() {
	}

	/**
	 * Crée une nouvelle instance de NombreNonValideException
	 * 
	 * @param message
	 *            Le message détaillant exception
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * Crée une nouvelle instance de NombreNonValideException
	 * 
	 * @param cause
	 *            L'exception à l'origine de cette exception
	 */
	public InvalidInputException(Throwable cause) {
		super(cause);
	}

	/**
	 * Crée une nouvelle instance de NombreNonValideException
	 * 
	 * @param message
	 *            Le message détaillant exception
	 * @param cause
	 *            L'exception à l'origine de cette exception
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}
}
