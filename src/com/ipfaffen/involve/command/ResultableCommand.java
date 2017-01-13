package com.ipfaffen.involve.command;

/**
 * @author Isaias Pfaffenseller
 */
public interface ResultableCommand<T> {

	/**
	 * @return
	 */
	T getResultValue();
	
	/**
	 * @return
	 */
	String getResultMessage();
	
	/**
	 * @throws CommandException
	 */
	void process() throws CommandException;
}
