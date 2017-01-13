package com.ipfaffen.involve.command;

/**
 * @author Isaias Pfaffenseller
 */
public class CommandException extends RuntimeException {

	/**
	 * @param message
	 */
	public CommandException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param args
	 */
	public CommandException(String message, Object... args) {
		super(String.format(message, args));
	}
}