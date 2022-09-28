package server;

public class Response {
    private int statusCode;
    private String message;
    private String contentType;
    private String content;

    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(int code){
        this.statusCode = code;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public void setContentType(String contentType){ this.contentType = contentType; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return  "HTTP/1.1 " + this.statusCode + " " + this.message + "\n" +
                "Content-Type: " + this.contentType + "\n" +
                "Content-Length: " + this.content.length() + "\n" +
                "\n" +
                this.content;
    }
}
