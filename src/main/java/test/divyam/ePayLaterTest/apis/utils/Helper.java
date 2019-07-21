package test.divyam.ePayLaterTest.apis.utils;

public class Helper {
	private String token;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public static String getAuthToken(String phoneNo) {
		return phoneNo;
	}
	

	public static boolean isValidToken(String validToken, String token) {
		if(validToken.contentEquals(token))
			return true;
		return false;
	}

}
