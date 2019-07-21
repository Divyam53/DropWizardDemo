package test.divyam.ePayLaterTest.apis.login;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentials;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtContext;
import com.google.common.collect.*;

import test.divyam.ePayLaterTest.apis.login.UserConfiguration;
import test.divyam.ePayLaterTest.apis.utils.AuthFilterUtils;
import test.divyam.ePayLaterTest.apis.login.LoginController;
//import com.tuturself.dropwizard.health.EmplyeeHealthCheck;

public class UserApplication extends Application<UserConfiguration> {

	public static void main(String[] args) throws Exception {
		new UserApplication().run(args);
		
	}

	@Override
	public void run(UserConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new LoginController());

		AuthFilterUtils authFilterUtils = new AuthFilterUtils();
		final AuthFilter<BasicCredentials, PrincipalImpl> basicFilter = authFilterUtils.buildBasicAuthFilter();
		final AuthFilter<JwtContext, MyUser> jwtFilter = authFilterUtils.buildJwtAuthFilter();

		final PolymorphicAuthDynamicFeature feature = new PolymorphicAuthDynamicFeature<>(
				ImmutableMap.of(PrincipalImpl.class, basicFilter, MyUser.class, jwtFilter));
		final AbstractBinder binder = new PolymorphicAuthValueFactoryProvider.Binder<>(
				ImmutableSet.of(PrincipalImpl.class, MyUser.class));

		environment.jersey().register(feature);
		environment.jersey().register(binder);
		environment.jersey().register(RolesAllowedDynamicFeature.class);
		
	}
}