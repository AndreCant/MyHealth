package it.univaq.mwt.myhealth.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	
	protected String title;
	protected String body;
	protected int vote;
}
