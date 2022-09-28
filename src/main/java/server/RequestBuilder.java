package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestBuilder {
    public static Request build(BufferedReader inputstream) throws IOException {

        // here we extract method, content-type, content-length, content, path
        // from our input-stream and build a request

        // helps us build a string out of input-stream from browser
        StringBuilder builder = new StringBuilder();

        // helps us seperating lines from inputstream in following loop
        String inputLine;

        // reads from input-stream headers out
        // loop reads until we reach an empty line
        while ((inputLine = inputstream.readLine()) != null && !inputLine.equals("")) {
            builder.append(inputLine).append(System.lineSeparator());
        }

        // builds a final string from headers
        String methodPathProtocolAndHeaders = builder.toString();

        // we get the length of our content from the headers through regex
        int contentLength = getContentLength(methodPathProtocolAndHeaders);
        char[] content = new char[contentLength];
        // asign the empty char array with values from input stream
        int read = inputstream.read(content, 0, content.length);

        // typecast content form char array to string
        // this is our final request body which can be processed further
        String request_body = new String(content);


        // we set all the request headers for demonstration
        Request request = new Request();
        request.setContent(methodPathProtocolAndHeaders);
        request.setMethod("");
        request.setPath("");
        request.setContentType("");
        request.setContentLength(contentLength);

        return request;
    }

    // method reads out content length using a regex
    private static int getContentLength(String methodPathProtocolAndHeaders) {
        Pattern r = Pattern.compile("^Content-Length:\\s(.+)", Pattern.MULTILINE);
        Matcher m = r.matcher(methodPathProtocolAndHeaders);

        if (!m.find()) {
            return 0;
        }

        return Integer.parseInt(m.group(1));
    }
}
