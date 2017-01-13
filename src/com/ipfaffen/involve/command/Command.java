package com.ipfaffen.involve.command;

/**
 * @author Isaias Pfaffenseller
 */
public abstract class Command {

	protected String command;
	
	/**
	 * @param command
	 */
	public Command(String command) {
		this.command = command;
	}
	
	/**
	 * @throws CommandException
	 */
	public abstract void process() throws CommandException;
}