package test.divyam.ePayLaterTest.apis.login;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.jersey.caching.CacheControl;
import test.divyam.ePayLaterTest.apis.utils.Helper;
import test.divyam.ePayLaterTest.apis.utils.ResponseEntity;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Timed
public class LoginController {
	public static final byte[] JWT_SECRET_KEY = "dfwzsdzwh823zebdwdz772632gdsbd3333".getBytes();


	@GET
	@Path("/{id}")
	@CacheControl(noCache = true, noStore = true, mustRevalidate = true, maxAge = 0)
	public final ResponseEntity doLogin(@Auth PrincipalImpl user) throws JoseException {
		return new ResponseEntity(buildToken(user).getCompactSerialization());
	}

	public JsonWebSignature buildToken(PrincipalImpl user) {
		//if (Helper.isValidId(id)) {
		final JwtClaims claims = new JwtClaims();
		//claims.setSubject("1");
		////claims.setStringClaim("roles", "user");
		claims.setStringClaim("user", user.getName());
		claims.setIssuedAtToNow();
		claims.setGeneratedJwtId();
		//System.out.println(id);
		final JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toString());
		jws.setAlgorithmHeaderValue(HMAC_SHA256);
		jws.setKey(new HmacKey(JWT_SECRET_KEY));
		return jws;
	}

}
