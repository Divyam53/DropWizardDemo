package test.divyam.ePayLaterTest.apis.login;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

import java.security.Principal;
import java.util.Optional;

import test.divyam.ePayLaterTest.apis.login.UserConfiguration;
import test.divyam.ePayLaterTest.apis.utils.JwtAuthFilter;
import test.divyam.ePayLaterTest.apis.login.LoginController;
//import com.tuturself.dropwizard.health.EmplyeeHealthCheck;

public class UserApplication extends Application<UserConfiguration> {

	public static void main(String[] args) throws Exception {
		new UserApplication().run(args);
		
	}

	@Override
	public void run(UserConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new LoginController());
		
		final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder()
            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
            .setRequireExpirationTime() // the JWT must have an expiration time
            .setRequireSubject() // the JWT must have a subject claim
            .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
            .setRelaxVerificationKeyValidation() // relaxes key length requirement
            .build(); // create the JwtConsumer instance

        environment.jersey().register(new AuthDynamicFeature(
            new JwtAuthFilter.Builder<MyUser>()
                .setJwtConsumer(consumer)
                .setRealm("realm")
                .setPrefix("Bearer")
                .setAuthenticator(new ExampleAuthenticator())
                .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new SecuredResource(configuration.getJwtTokenSecret()));
		
	}
    private static class ExampleAuthenticator implements Authenticator<JwtContext, MyUser> {

        @Override
        public Optional<MyUser> authenticate(JwtContext context) {
            // Provide your own implementation to lookup users based on the principal attribute in the
            // JWT Token. E.g.: lookup users from a database etc.
            // This method will be called once the token's signature has been verified

            // In case you want to verify different parts of the token you can do that here.
            // E.g.: Verifying that the provided token has not expired.

            // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

            try {
                final String subject = context.getJwtClaims().getSubject();
                if ("good-guy".equals(subject)) {
                    return Optional.of(new MyUser("80980988"));
                }
                return Optional.empty();
            }
            catch (MalformedClaimException e) { return Optional.empty(); }
        }
    }

}