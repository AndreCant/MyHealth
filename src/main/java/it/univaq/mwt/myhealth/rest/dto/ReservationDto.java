package it.univaq.mwt.myhealth.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto implements Serializable{
	
	private long id;
	private LocalDate reservationDate;
	private LocalDateTime startHour;
	private LocalDateTime endHour;
	private String examImageUrl;
	private String examName;
	
}
