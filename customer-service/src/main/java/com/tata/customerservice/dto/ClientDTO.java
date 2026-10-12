package com.tata.customerservice.dto;

import com.tata.customerservice.types.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
	
	private Long clientId;
	
	@NotNull(message = "Name is required.")
	@Size(max = 150)
	private String name;
	
	private Gender gender;
	
	@NotNull(message = "Age is required.")
	@Min(value = 18, message = "Minimun age to have an acccount is 18 years.")
	private Integer age;
	
	@NotNull(message = "Identification is required.")
	@Size(max = 15, min = 5, message = "Identification must be have beteen 5 and 15 characters.")
	private String identification;
	
	@Size(max = 255, message = "Address exceeds the size allowed.")
	private String address;
	
	@Size(max = 15, message = "Phone exceeds the size allowed.")
	private String phone;
	
	@Pattern(regexp = "^[a-z0-9]{4,10}$", 
			message = "Password must contain at least one number and one uppercase "
					+ "and lowercase letter, and at least 8 or more characters.")
	private String password;
	
	private boolean status;
}
