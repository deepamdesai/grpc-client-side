package my.groupId;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    
    @Inject
    EchoGrpcClient echoGrpcClient;

    @GET
    @Path("/grpc")
    @Produces(MediaType.TEXT_PLAIN)
    public String echo() {
        return echoGrpcClient.sendMessage("Hello from Quarkus gRPC - Done!");
    }
}
