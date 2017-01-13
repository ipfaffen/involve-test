package com.ipfaffen.involve.command;

import static com.ipfaffen.involve.util.Regex.matches;

import java.util.regex.Matcher;

import com.ipfaffen.involve.model.dao.ModelDao;

/**
 * @author Isaias Pfaffenseller
 */
public class CountDistinctByCommand extends Command implements ResultableCommand<Long> {

	public static final String REGEX = "count distinct ([a-z\\_]+)";

	private ModelDao<?> dao;
	private Long resultValue;
	private String resultMessage;

	/**
	 * @param command
	 * @param dao
	 */
	public CountDistinctByCommand(String command, ModelDao<?> dao) {
		super(command);
		this.dao = dao;
	}

	@Override
	public void process() throws CommandException {
		Matcher matcher = matches(command, REGEX);
		String column = matcher.group(1);
		Long count = dao.countBy(column);
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