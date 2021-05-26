package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "frontoffice")
public class FrontOffice extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int number;
	
	public FrontOffice() {}
}
