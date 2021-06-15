package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "invoice")
public class Invoice implements Serializable{
	
	@Id
    protected String index;
    
    @Version
    protected long version;
    
	private static final long serialVersionUID = 1L;
	
	private int numbPayments;
	private int tax;
	private int code;
	private Double totalPrice;
	private Double finalPrice;
	private LocalDate issueDate;
}
