package it.univaq.mwt.myhealth.rest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorDto extends RegistrationDto implements Serializable{
	
	protected Long id;
	protected String urlImage;
	private String specialization;
	private String skills;
	private int age;
}
