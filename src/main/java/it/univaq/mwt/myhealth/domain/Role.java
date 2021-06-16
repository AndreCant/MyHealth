package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "role")
    @JsonManagedReference
	private List<User> users;
}
