package test.divyam.ePayLaterTest.apis.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Reponse returned to user for demo purposes, indicating username and role that was present in JWT.
 *
 * @author Hendrik van Huyssteen
 * @since 21 Sep 2017
 */
public class ProtectedResourceResponse {

	public ProtectedResourceResponse(String username) {
		this.username = username;
	}


	@JsonProperty("username")
	public String username;
}