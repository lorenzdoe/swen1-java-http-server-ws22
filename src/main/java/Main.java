import application.DemoApplication;
import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args){

        Server server = new Server(new DemoApplication());

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}