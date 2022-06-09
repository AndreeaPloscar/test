package sensor.tcp;



import common.message.Message;
import common.utils.RemotingConstants;

import java.io.IOException;
import java.net.Socket;


public class TcpClient {

    public TcpClient() { }

    public Message sendAndReceive(Message request) throws Exception {
        try (var socket = new Socket(RemotingConstants.HOST, RemotingConstants.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()) {
//            System.out.println("sending request: " + request);
            request.writeTo(os);
//            System.out.println("request sent");
            Message response = new Message();
            response.readFrom(is);
//            System.out.println("received response: " + response);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("exception in send and receive:" +  e.getMessage());
        }

    }
}
