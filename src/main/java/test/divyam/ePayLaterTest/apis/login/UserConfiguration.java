package test.divyam.ePayLaterTest.apis.login;

import java.io.UnsupportedEncodingException;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class UserConfiguration extends Configuration{
    @NotEmpty
    private String jwtTokenSecret = "dfwzsdzwh823zebdwdz772632gdsbd";

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
	
}
