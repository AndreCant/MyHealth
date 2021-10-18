package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@OneToMany(mappedBy = "frontOffice", cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<Expense> expenses;
	
	@OneToMany(mappedBy = "frontOffice")
    @JsonManagedReference
	private List<Reservation> reservations;
	
	public FrontOffice() {}
}
