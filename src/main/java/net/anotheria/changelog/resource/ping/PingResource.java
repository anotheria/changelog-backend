package net.anotheria.changelog.resource.ping;

import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.changelog.ChangeLogAPI;
import net.anotheria.changelog.resource.BaseResource;
import net.anotheria.changelog.resource.ReplyObject;
import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Ping Resource to check if app is up
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ping")

@Monitor(category = "resource")
public class PingResource extends BaseResource {

	private static ChangeLogAPI api = APIFinder.findAPI(ChangeLogAPI.class);

	@GET
	@Path("/")
	public ReplyObject ping(){
		return ReplyObject.success();
	}


}
