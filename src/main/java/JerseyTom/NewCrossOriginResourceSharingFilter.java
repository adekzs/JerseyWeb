package JerseyTom;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class NewCrossOriginResourceSharingFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Origin","*");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Methods","GET, POST, DELETE, UPDATE");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Headers","X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");

    }
}
