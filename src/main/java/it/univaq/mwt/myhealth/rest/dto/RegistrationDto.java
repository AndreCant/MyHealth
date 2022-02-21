package it.univaq.mwt.myhealth.rest.dto;

import java.time.LocalDate;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto extends LoginDto{
	
	protected String email;
	protected String name;
	protected String surname;
	protected LocalDate dateOfBirth;
	protected String gender;
	protected String fiscalCode;
	
}
