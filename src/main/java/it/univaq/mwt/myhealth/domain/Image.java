package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image extends AbstractEntity implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String name;
	private String url;
	
	@ManyToOne
    @JsonBackReference
	private Exam exam;
}
