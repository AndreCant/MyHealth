package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
