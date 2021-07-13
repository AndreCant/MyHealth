package it.univaq.mwt.myhealth.util;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

@Component
public class DataInitializer {

	@Autowired
	UserService userService;
	
	private List<Role> roles;
	
	@EventListener(ApplicationReadyEvent.class)
	public void initialize() {
		this.initRoles();
		this.initUsers();
	}
	
	private void initRoles() {
		try {
			this.roles = List.of(
				ObjectFactory.createRole("admin", "System Administrator"),
				ObjectFactory.createRole("doctor", "Doctor User"),
				ObjectFactory.createRole("patient", "Patient User")
			);
			
			userService.saveRoles(new ArrayList<Role>(this.roles));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initUsers() {
		try {
			userService.saveUsers(new ArrayList<User>(List.of(
				ObjectFactory.createUser("andrea95", "and@and.it", "admin123", this.roles.get(0)),
				ObjectFactory.createUser("umberto355", "umb@umb.com", "admin123", this.roles.get(1)),
				ObjectFactory.createUser("lello21", "lol@lol.com", "admin123", this.roles.get(2))
			)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
