package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "invoice")
public class Invoice implements Serializable{
	
	@Id
    private String index;
    
    @Version
    private long version;
    
	private static final long serialVersionUID = 1L;

	private int code;
	private int numbPayments;
	private int tax;
	private Double totalPrice;
	private Double finalPrice;
	private LocalDate issueDate;
	private Long payment_id;
	
}
