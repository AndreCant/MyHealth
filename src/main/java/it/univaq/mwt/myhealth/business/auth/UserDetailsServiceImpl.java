package it.univaq.mwt.myhealth.business.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User utente;
		try {
			utente = service.findUserByUsername(username);
			if (utente != null) {
				return new UserDetailsImpl(utente);
			} else {
				throw new UsernameNotFoundException("User not found!");
			}

		} catch (BusinessException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User not found!");
		}
	}

}
