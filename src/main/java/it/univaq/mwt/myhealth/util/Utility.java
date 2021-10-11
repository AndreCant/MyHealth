package it.univaq.mwt.myhealth.util;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility {
	
	public static int getRandomNumberInRange(int min, int max) {
        return new Random().ints(min, (max + 1)).findFirst().getAsInt();
    }
	
	public static String encodePassword(String password) {
		return (new BCryptPasswordEncoder()).encode(password);
	}
	
	public static String getExamType(String type) {
		switch (type) {
			case "exam": return "e";
			case "rehabilitation path": return "r";
			case "e": return "exam";
			case "r": return "rehabilitation path";
			default: return null;
		}
	}
}
