package it.univaq.mwt.myhealth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

public class ObjectFactory {
	
	private ObjectFactory() {}
	
	public static Role createRole(String name, String description) {
		return new Role(name, description);
	}
	
	public static User createUser(String username, String email, String password, Role role) {
		String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
		return new User(username, email, encodedPassword, role);
	}
}
