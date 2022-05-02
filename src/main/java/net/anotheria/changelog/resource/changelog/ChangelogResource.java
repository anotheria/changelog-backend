package net.anotheria.changelog.resource.changelog;

import net.anotheria.anoplass.api.APIException;
import net.anotheria.anoplass.api.APIFinder;
import net.anotheria.changelog.api.changelog.ChangeLogAPI;
import net.anotheria.changelog.api.changelog.bean.ChangeLogAO;
import net.anotheria.changelog.resource.BaseResource;
import net.anotheria.changelog.resource.ReplyObject;
import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
public class ChangelogResource extends BaseResource {

	private static ChangeLogAPI changeLogAPI = APIFinder.findAPI(ChangeLogAPI.class);

	@GET
	@Path("/{id}")
//	@SecurityRequirement(name = "authToken")
	public ReplyObject get(@PathParam("id") Integer id){
		try {
			return ReplyObject.success("data", changeLogAPI.get(id));
		} catch(APIException e){
			return ReplyObject.error(e);
		}
	}

	@POST
	@Path("/save")
//	@SecurityRequirement(name = "authToken")
	public ReplyObject save(ChangeLogAO req) throws APIException {
		changeLogAPI.save(req);
		return ReplyObject.success();
	}

	@POST
	@Path("/list")
//	@SecurityRequirement(name = "authToken")
	public ReplyObject list() throws APIException {
		List<ChangeLogAO> list = changeLogAPI.list();
		list.addAll(changeLogAPI.getEntries());
		return ReplyObject.success("data", list);
	}

    @GET
    @Path("/types")
//	@SecurityRequirement(name = "authToken")
    public ReplyObject types() throws APIException {
        return ReplyObject.success("data", changeLogAPI.getTypes());
    }

	@GET
	@Path("/tags")
//	@SecurityRequirement(name = "authToken")
	public ReplyObject tags() throws APIException {
		return ReplyObject.success("data", changeLogAPI.getTags());
	}

	@GET
	@Path("/entries")
	public ReplyObject entries(){
		try {
			return ReplyObject.success("entries", changeLogAPI.getEntries());
		}catch(APIException e){
			return ReplyObject.error(e);
		}
	}                          

	@GET
	@Path("/entries/{criteria}")
	public ReplyObject search(@PathParam("criteria") String criteria){
		try {
			return ReplyObject.success("entries", changeLogAPI.searchEntries(criteria));
		}catch(APIException e){
			return ReplyObject.error(e);
		}
	}

}
