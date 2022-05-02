package net.anotheria.changelog.resource.mock;

import net.anotheria.changelog.resource.BaseResource;
import net.anotheria.changelog.resource.ReplyObject;
import net.anotheria.changelog.resource.mock.bean.MockRequest;
import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Monitor
@Path("/mock")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MockResource extends BaseResource {

    @POST
    @Path("/")
    public ReplyObject mockRequest(MockRequest req) {
        return ReplyObject.success("data", req.getResponse());
    }

}
