package server.tcp;



import common.converter.SensorConverter;
import common.message.Message;
import common.utils.RemotingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    private final ExecutorService executorService;
    private final Map<String, UnaryOperator<Message>> methodHandlers;

    @Autowired
    private RestTemplate restTemplate;

    public TcpServer(ExecutorService executorService) {
        this.executorService = executorService;
        this.methodHandlers = new HashMap<>();

    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(RemotingConstants.PORT)) {
            System.out.println("server started; waiting for clients...");
            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                executorService.submit(new ClientHandler(clientSocket));
                try {
                    Thread.sleep((new Random().nextInt(3) + 1) * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (var is = socket.getInputStream();
                 var os = socket.getOutputStream()) {



                Message request = new Message();
                request.readFrom(is);
                System.out.println("received request: " + request.getBody());
                String name = SensorConverter.sensorFromMessage(request.getBody()).getName();
                System.out.println(name);
                String isActive = "";
                //read the request (of type model.Message) from client
                isActive = restTemplate.getForObject("http://localhost:8080/api/sensors/isActive/" + name, String.class);
                System.out.println("===================");
                System.out.println(isActive);
                if(isActive != null) {
                    if (isActive.equals("false")) {
                        Message response = new Message(Message.STOP, "STOP CLIENT");
//                System.out.println("computed response: " + response);

                        //send response (of type model.Message) to client
                        response.writeTo(os);
                        return;
                    }
                }
                // compute response (of type model.Message)
                Message response = methodHandlers.get(request.getHeader()).apply(request);
//                System.out.println("computed response: " + response);

                //send response (of type model.Message) to client
                response.writeTo(os);



//                System.out.println("response sent to client");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
