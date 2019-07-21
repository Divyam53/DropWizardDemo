package test.divyam.ePayLaterTest.apis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Authorizer;
import test.divyam.ePayLaterTest.apis.login.MyUser;

/**
 * Determines if a user is authorised to access an API endpoint, after they were authenticated with {@link JwtAuthenticator}.
 *
 * See {@link com.hendrikvh.demos.jwtdemo.resources.ProtectedResourceOne}.
 *
 * @author Hendrik van Huyssteen
 * @since 21 Sep 2017
 */
public class JWTAuthoriser implements Authorizer<MyUser> {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthoriser.class);

	@Override
	public boolean authorize(MyUser exampleUser, String requiredRole) {
//		if (exampleUser == null) {
//			LOGGER.warn("msg=user object was null");
//			return false;
//		}
//
//		String roles = null;
//		if (roles == null) {
//			LOGGER.warn("msg=roles were null, user={}, userId={}", exampleUser.getName());
//			return false;
//		}
//		return roles.contains(requiredRole);
		return true;
	}
}