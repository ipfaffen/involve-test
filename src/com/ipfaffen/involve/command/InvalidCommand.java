package com.ipfaffen.involve.command;

/**
 * @author Isaias Pfaffenseller
 */
public class InvalidCommand extends Command implements ResultableCommand<String> {

	private String message;
	
	/**
	 * @param command
	 */
	public InvalidCommand(String command) {
		super(command);
	}

	@Override
	public void process() {
		message = "I have no idea what you are talking about.";
	}

	@Override
	public String getResultValue() {
		return null;
	}
	
	@Override
	public String getResultMessage() {
		return message;
	}
}