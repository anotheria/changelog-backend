package net.anotheria.changelog.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;

/**
 * Base class for resources to hold apis.
 *
 */
public abstract class BaseResource {
    protected Logger log = LoggerFactory.getLogger(this.getClass());


    public BaseResource() {
    }

    protected String getFileContentDisposition(String fileName) {
        return "attachment; filename = " + fileName;
    }

    protected void logError(String msg, HttpServletRequest request, UriInfo uriInfo, Exception e) {
//        UUID uuid = UUID.randomUUID();
//        log.error("UUID({}) - " + msg, uuid, e);
//        errorHandleAPI.errorHandle(uuid, e, request, uriInfo);
    }
}
