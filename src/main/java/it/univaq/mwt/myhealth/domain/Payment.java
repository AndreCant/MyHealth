package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Double price;
	private int discount;
	private boolean isPaid;
	private int tax;
	private Double finalPrice;
	
	@OneToOne(cascade = CascadeType.DETACH, mappedBy = "payment")
	private Visit visit;
		
	public Payment() {}
}
