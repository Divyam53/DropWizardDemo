package test.divyam.ePayLaterTest.apis.login;

import javax.annotation.Generated;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import io.dropwizard.auth.Auth;
import test.divyam.ePayLaterTest.apis.utils.AuthService;
import test.divyam.ePayLaterTest.apis.utils.ProtectedResourceResponse;

@Path("/spend")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class SpendController {
	
	@GET
	@Path("/{id}")
	public String getSpend(@PathParam("id") String id) throws JoseException {
		AuthService service = AuthService.getInstance();
		return service.getValue(id).getCompactSerialization();
	}

}
