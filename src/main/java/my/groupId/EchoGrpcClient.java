package my.groupId;

import com.example.grpcbin.EchoRequest;
import com.example.grpcbin.EchoResponse;
import com.example.grpcbin.EchoServiceGrpc;
import org.jboss.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EchoGrpcClient {
    private static final Logger LOG = Logger.getLogger(EchoGrpcClient.class);

    private final EchoServiceGrpc.EchoServiceBlockingStub blockingStub;

    public EchoGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        blockingStub = EchoServiceGrpc.newBlockingStub(channel);
    }

    public String sendMessage(String message) {
        EchoRequest request = EchoRequest.newBuilder().setMessage(message).build();
        EchoResponse response = blockingStub.echo(request);
        LOG.info("Received response: " + response.getMessage());
        return response.getMessage();
    }
}
