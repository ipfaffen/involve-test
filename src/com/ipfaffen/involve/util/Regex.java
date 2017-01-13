package com.ipfaffen.involve.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Isaias Pfaffenseller
 */
public class Regex {

	/**
	 * @param str
	 * @param regex
	 * @return
	 */
	public static Matcher matches(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		matcher.matches();
		return matcher;
	}
}