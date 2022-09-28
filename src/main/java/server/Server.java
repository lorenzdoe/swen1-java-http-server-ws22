package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 10001;
    private ServerSocket server;
    private final Application application;
    //Constructor
    public Server(Application application){
        this.application = application;
    }


    //starts server, opens serversocket
    public void start() throws IOException {

        System.out.println("Server started");
        server = new ServerSocket(PORT, 5);
        System.out.println("Server running at: http://localhost:" + PORT);

        run();
    }

    private void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                // TODO: Implement RequestHandler
                // handles input and output from socket
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                Request request = RequestBuilder.build(in);

                // we build a sample response for demonstration purposes
                Response response = application.handle(request);

                // writes out response-content in browser
                out.write(response.toString());

                // close input-, and output-stream
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
