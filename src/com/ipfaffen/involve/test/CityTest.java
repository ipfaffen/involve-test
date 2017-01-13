package com.ipfaffen.involve.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ipfaffen.involve.command.Command;
import com.ipfaffen.involve.command.CommandFactory;
import com.ipfaffen.involve.command.InvalidCommand;
import com.ipfaffen.involve.command.ResultableCommand;
import com.ipfaffen.involve.context.AppContext;

/**
 * @author Isaias Pfaffenseller
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CityTest {

	private CommandFactory commandFactory = new CommandFactory(AppContext.getCityDao());
	
	@Test
	public void invalidCommandSuccess() {
		Command command = commandFactory.getCommand("Yoda");
		assertTrue(command instanceof InvalidCommand);
	}
	
	@Test
	public void countAllCommandSuccess() {
		commandFactory.getCommand("count *").process();
	}
	
	@Test
	public void countDistinctCommandSuccess() {
		commandFactory.getCommand("count distinct uf").process();
	}
	
	@Test
	public void filterByCommandSuccess() {
		commandFactory.getCommand("filter uf sc").process();
	}
	
	@Test(expected = RuntimeException.class)
	public void countDistinctInvalidColumnFail() {
		commandFactory.getCommand("count distinct jedi").process();
	}
	
	@Test(expected = RuntimeException.class)
	public void filterByInvalidColumnFail() {
		commandFactory.getCommand("filter pink Floyd").process();
	}
	
	@Test
	public void filterByNoResultSuccess() {
		ResultableCommand<List<String>> command = (ResultableCommand) commandFactory.getCommand("filter name Hendrix");
		command.process();
		Assert.assertTrue(command.getResultValue().isEmpty());
	}
	
	@Test
	public void filterByResultSuccess() {
		ResultableCommand<List<String>> command = (ResultableCommand) commandFactory.getCommand("filter name Venâncio Aires");
		command.process();
		Assert.assertEquals(1, command.getResultValue().size());
	}
	
	@Test
	public void countDistinctResultSuccess() {
		ResultableCommand<Long> command = (ResultableCommand) commandFactory.getCommand("count distinct uf");
		command.process();
		Assert.assertEquals(new Long(27), command.getResultValue());
	}
}