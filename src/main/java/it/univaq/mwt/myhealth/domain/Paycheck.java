package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.Month;
import java.time.Year;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "paycheck")
public class Paycheck implements Serializable{
	
	@Id
    private String index;
    
    @Version
    private long version;
    
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private Integer register;
	private String code;
	private Integer year;
	private Integer month;
	private int days;
	private String description;
	private Double grossSalary;
	private Double totalTax;
	private Double netSalary;
}
