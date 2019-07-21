package test.divyam.ePayLaterTest.apis.utils;

import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import test.divyam.ePayLaterTest.apis.login.MyUser;

/**
 * Auth filter utilities used elsewhere, just to keep all the auth config nice and central.
 *
 * @author Hendrik van Huyssteen
 * @since 21 Sep 2017
 */
public class AuthFilterUtils {

	public BasicCredentialAuthFilter<PrincipalImpl> buildBasicAuthFilter() {
		return new BasicCredentialAuthFilter.Builder<PrincipalImpl>().setAuthenticator(new BasicAuthenticator()).setPrefix("Basic")
				.buildAuthFilter();
	}

	public AuthFilter<JwtContext, MyUser> buildJwtAuthFilter() {
		// These requirements would be tightened up for production use
		final JwtConsumer consumer = new JwtConsumerBuilder().setAllowedClockSkewInSeconds(300).setRequireSubject()
				.setVerificationKey(new HmacKey(Constants.JWT_SECRET_KEY)).build();

		return new JwtAuthFilter.Builder<MyUser>().setJwtConsumer(consumer).setRealm("realm").setPrefix("Bearer")
				.setAuthenticator(new JWTAuthenticator()).setAuthorizer(new JWTAuthoriser()).buildAuthFilter();
	}
}