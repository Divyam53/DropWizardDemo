package test.divyam.ePayLaterTest.apis.login;

import java.util.Date;

import javax.annotation.Generated;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Param;
import org.jose4j.http.Response;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import io.dropwizard.auth.Auth;
import test.divyam.ePayLaterTest.apis.utils.AuthService;
import test.divyam.ePayLaterTest.apis.utils.Helper;
import test.divyam.ePayLaterTest.apis.utils.ProtectedResourceResponse;
import test.divyam.ePayLaterTest.apis.utils.Transaction;

@Path("/spend")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class SpendController {
	
	@POST
	@Path("/{id}")
	public Response getSpend(@PathParam("id") String id, @QueryParam("token") String token) throws JoseException {
		AuthService service = AuthService.getInstance();
		String validToken = service.getValue(id).getCompactSerialization();
		if(Helper.isValidToken(validToken,token)) {
			Transaction dummyTrans = new Transaction(234234324,"First Transaction",new Date("2019-11-11"));
			JSONObject json = new JSONObject(dummyTrans.getKeyValuePair());
			return new Response(200, "Success",null,json.toString());
		}
		else return new Response(401, "UnAuthorised Access", null, null);
	}

}
