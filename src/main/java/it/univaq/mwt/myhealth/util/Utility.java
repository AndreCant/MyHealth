package it.univaq.mwt.myhealth.util;

import java.util.List;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univaq.mwt.myhealth.domain.Review;

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
	
	public static int getRaiting(List<Review> reviews) {
		int raiting = 0;
		int nReviews = reviews.size();
		
		if (nReviews > 0) {
			int totalVotes = 0;
			for (Review review : reviews) {
				totalVotes += review.getVote();
			}
			
			if (totalVotes > 0) {
				raiting = (int) (totalVotes / nReviews);
			}
		}
		return raiting;
	}
}
