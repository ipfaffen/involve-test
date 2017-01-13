package com.ipfaffen.involve.command;

import com.ipfaffen.involve.model.dao.ModelDao;

/**
 * @author Isaias Pfaffenseller
 */
public class CommandFactory {

	private ModelDao<?> dao;
	
	/**
	 * @param dao
	 */
	public CommandFactory(ModelDao<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @param command
	 * @return
	 */
	public Command getCommand(String command) {
		if(command.matches(CountAllCommand.REGEX)) {
			return new CountAllCommand(command, dao);
		}
		else if(command.matches(CountDistinctByCommand.REGEX)) {
			return new CountDistinctByCommand(command, dao);
		}
		else if(command.matches(FilterByCommand.REGEX)) {
			return new FilterByCommand(command, dao);
		}
		return new InvalidCommand(command);
	}
}