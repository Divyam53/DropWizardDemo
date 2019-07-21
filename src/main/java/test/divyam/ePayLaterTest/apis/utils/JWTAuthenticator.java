package test.divyam.ePayLaterTest.apis.utils;

import java.util.Optional;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Authenticator;
import test.divyam.ePayLaterTest.apis.login.MyUser;

/**
 * @author Hendrik van Huyssteen
 * @since 21 Sep 2017
 */
public class JWTAuthenticator implements Authenticator<JwtContext, MyUser> {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticator.class);

	/**
	 * Extracts user roles from Jwt. This method will be called once the token's signature has been verified.
	 * <p>
	 * All JsonWebTokenExceptions will result in a 401 Unauthorized response.
	 */
	@Override
	public Optional<MyUser> authenticate(JwtContext context)  {
		try {
			JwtClaims claims = context.getJwtClaims();
			
			String username = (String) claims.getClaimValue("user");
			//String roles = (String) claims.getClaimValue("roles");

			return Optional.of(new MyUser(username));
		} catch (Exception e) {
			LOGGER.warn("msg=Failed to authorise user: {}", e.getMessage(), e);
			return Optional.empty();
		}
	}
}
