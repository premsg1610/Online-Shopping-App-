package com.masai.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserSessionDTO {

	
    private Integer customerId;
	
	private String uuid;
	
	private LocalDateTime localDateTime;
	
}

