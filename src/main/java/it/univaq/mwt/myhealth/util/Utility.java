package it.univaq.mwt.myhealth.util;

import java.util.Random;

public class Utility {
	
	public static int getRandomNumberInRange(int min, int max) {
        return new Random().ints(min, (max + 1)).findFirst().getAsInt();
    }
}
