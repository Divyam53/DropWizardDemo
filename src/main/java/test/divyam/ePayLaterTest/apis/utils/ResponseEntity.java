package test.divyam.ePayLaterTest.apis.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEntity {
	
	@JsonProperty("token")
	private String token;
	
	public ResponseEntity(String token) {
		this.token = token;
	}
	
	

}
