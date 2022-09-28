package server;

public interface Application {
    Response handle(Request request);
}
