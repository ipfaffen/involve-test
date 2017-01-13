package com.ipfaffen.involve;

import java.util.Scanner;

import com.ipfaffen.involve.command.Command;
import com.ipfaffen.involve.command.CommandException;
import com.ipfaffen.involve.command.CommandFactory;
import com.ipfaffen.involve.command.ResultableCommand;
import com.ipfaffen.involve.context.AppContext;

/**
 * @author Isaias Pfaffenseller
 */
public class Main {

	/**
	 * @param args
	 */
	public static void mainX(String[] args) {
		System.out.println(AppContext.getCityDao().countAll());
		System.out.println(AppContext.getCityDao().countBy("ibge_id"));
		System.out.println(AppContext.getCityDao().countBy("uf"));
		System.out.println(AppContext.getCityDao().countBy("name"));
		System.out.println(AppContext.getCityDao().countBy("capital"));
		System.out.println(AppContext.getCityDao().countBy("alternative_names"));
		System.out.println(AppContext.getCityDao().countBy("microregion"));
		System.out.println(AppContext.getCityDao().countBy("mesoregion"));
		System.out.println(AppContext.getCityDao().findBy("uf", "RO").size());
		AppContext.getCityDao().findBy("uf", "RO").forEach(city -> System.out.println(city.getName()));
		AppContext.getCityDao().getRowsBy("uf", "RO").forEach(city -> System.out.println(city));
	}
	
	private static CommandFactory commandFactory;
	static {
		commandFactory = new CommandFactory(AppContext.getCityDao());
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Enable scanner so the user can type the commands on console.
		try(Scanner sc = new Scanner(System.in)) {
			while(sc.hasNext()){
				processInput(sc.nextLine());
			}
		}
	}
	
	/**
	 * @param input
	 */
	public static void processInput(String input) {
		input = input.trim();
		if(input.isEmpty()) {
			return;
		}
		try {
			Command command = commandFactory.getCommand(input);
			command.process();
			if(command instanceof ResultableCommand) {
				System.out.println("-");
				System.out.println(((ResultableCommand<?>) command).getResultMessage());
				System.out.println("-");
			}
		}
		catch(CommandException e) {
			System.out.println("-");
			System.out.println(e.getMessage());
			System.out.println("-");
		}
		catch(Exception e) {
			System.out.println("-");
			System.out.println(String.format("Invalid command caused by:\r\n%s", e.getMessage()));
			System.out.println("-");
		}
	}
}