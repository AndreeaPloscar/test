package common.message;

import java.io.*;

public class Message {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String OK = "ok";
    public static final String ERROR = "error";
    public static final String SEPARATOR = ";";
    public static final String STOP = "STOP";

    private String header;
    private String body;

    public Message() {
    }

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }


    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "model.Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public void readFrom(InputStream is) throws IOException {
        var br = new BufferedReader(new InputStreamReader(is));
        header = br.readLine();
        body = br.readLine();
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
