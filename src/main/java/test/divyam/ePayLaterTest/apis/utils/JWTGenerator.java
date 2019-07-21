package test.divyam.ePayLaterTest.apis.utils;

import static java.util.Collections.singletonMap;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

import java.security.Principal;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;

import io.dropwizard.auth.Auth;

public class JWTGenerator {
	private final byte[] tokenSecret;

    public JWTGenerator(byte[] tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public Map<String, String> generateExpiredToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setSubject("good-guy");

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return singletonMap("token", jws.getCompactSerialization());
        }
        catch (JoseException e) { throw Throwables.propagate(e); }
    }

    public Map<String, String> generateValidToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("good-guy");
        claims.setExpirationTimeMinutesInTheFuture(30);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return singletonMap("token", jws.getCompactSerialization());
        }
        catch (JoseException e) { throw Throwables.propagate(e); }
    }

    public Map<String, Object> get(@Auth Principal user) {
        return ImmutableMap.<String, Object>of("username", user.getName());
    }

}
