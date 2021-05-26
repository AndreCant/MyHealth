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
@Table(name = "payment")
public class Payment extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Double price;
	private int discount;
	private boolean isPaid;
	private int tax;
	private Double finalPrice;
	private String invoice; //to verify, ex id to mongo
	
//	private Visit visit;
//	private Convention convention;
	
	public Payment() {}
}
