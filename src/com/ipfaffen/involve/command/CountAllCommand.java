package com.ipfaffen.involve.command;

import com.ipfaffen.involve.model.dao.ModelDao;

/**
 * @author Isaias Pfaffenseller
 */
public class CountAllCommand extends Command implements ResultableCommand<Long> {

	public static final String REGEX = "count \\*";

	private ModelDao<?> dao;
	private Long resultValue;
	private String resultMessage;
	
	/**
	 * @param command
	 * @param modelDao
	 */
	public CountAllCommand(String command, ModelDao<?> dao) {
		super(command);
		this.dao = dao;
	}

	@Override
	public void process() throws CommandException {
		Long count = dao.countAll();
		resultValue = count;
		resultMessage = count.toString();
	}

	@Override
	public Long getResultValue() {
		return resultValue;
	}

	@Override
	public String getResultMessage() {
		return resultMessage;
	}
}