package com.example.tmdb.util;

import org.springframework.util.StringUtils;

public class TMDBUtil {

	
	public static boolean isValidNumber(String input) {
		String validChars = "0123456789";
		char Char;
		boolean isNumber = true;
		if(StringUtils.isEmpty(input)) {
			return false;
		}
		
		for(int i=0;i<input.length() && isNumber == true;i++) {
			Char = input.charAt(i);
			if(validChars.indexOf(Char) == -1) {
				isNumber = false;
				break;
			}
		}
		return isNumber;
	}
}
