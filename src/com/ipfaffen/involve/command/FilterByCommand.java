package com.ipfaffen.involve.command;

import static com.ipfaffen.involve.util.Regex.matches;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import com.ipfaffen.involve.model.dao.ModelDao;

/**
 * @author Isaias Pfaffenseller
 */
public class FilterByCommand extends Command implements ResultableCommand<List<String>> {

	public static final String REGEX = "filter ([a-z\\_]+) ([A-Za-zÀ-ú0-9\\s]+)";

	private ModelDao<?> dao;
	private List<String> resultValue;
	private String resultMessage;
	
	/**
	 * @param command
	 * @param dao
	 */
	public FilterByCommand(String command, ModelDao<?> dao) {
		super(command);
		this.dao = dao;
	}

	@Override
	public void process() throws CommandException {
		Matcher matcher = matches(command, REGEX);
		String filterCol = matcher.group(1);
		String filterVal = matcher.group(2);
		List<String> rows = dao.getRowsBy(filterCol, filterVal);
		resultValue = rows.stream().collect(Collectors.toList());
		rows.add(0, dao.getHeader());
		resultMessage = rows.stream().collect(Collectors.joining("\r\n"));
	}

	@Override
	public List<String> getResultValue() {
		return resultValue;
	}

	@Override
	public String getResultMessage() {
		return resultMessage;
	}
}