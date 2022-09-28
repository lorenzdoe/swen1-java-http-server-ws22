package application;

import server.Application;
import server.Request;
import server.Response;

public class DemoApplication implements Application {

    public Response handle(Request request){

        Response response = new Response();

        // we build a sample response to check if it works
        response.setStatusCode(200);
        response.setMessage("OK");
        response.setContentType("text/plain");
        response.setContent(request.getContent()); // we passed the input request as content

        return response;
    }
}
