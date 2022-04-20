package net.anotheria.changelog.resource;

import net.anotheria.anoplass.api.APIException;
import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.changelog.ChangeLogAPI;
import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:43
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/changelog")

@Monitor(category = "resource")
public class ChangelogResource {

	private static ChangeLogAPI api = APIFinder.findAPI(ChangeLogAPI.class);

	@GET
	@Path("/ping")
	public ReplyObject ping(){
		return ReplyObject.success();
	}

	@GET
	@Path("/entries")
	public ReplyObject entries(){
		try {
			return ReplyObject.success("entries", api.getEntries());
		}catch(APIException e){
			return ReplyObject.error(e);
		}
	}                          

	@GET
	@Path("/entries/{criteria}")
	public ReplyObject search(@PathParam("criteria") String criteria){
		try {
			return ReplyObject.success("entries", api.searchEntries(criteria));
		}catch(APIException e){
			return ReplyObject.error(e);
		}
	}

}
