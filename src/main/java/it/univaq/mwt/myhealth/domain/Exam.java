package it.univaq.mwt.myhealth.domain;

import java.awt.TextArea;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "exam")
public class Exam extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private int session;
	private String type;
	private String name;
	private String specialization;
	private String subSpecialization;
	@Column(length = 10000)
	private String description;
	private Double price;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Reservation> reservations;

	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Image> images;
}
