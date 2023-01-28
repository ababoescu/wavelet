import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    //Scanner stringS = new Scanner(System.in);
    String s = new String();
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Ana Maria's Number: %d", num);
        } //else if (url.getPath().equals("/add-message"))
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for(int i = 1; i < parameters.length; i++){
                        //s = parameters[i];
                        return String.format("%s\n", parameters[i]);
                    }
                    //s = parameters[1];
                    
                    //return String.format("your word is = %s\n", parameters[1]);
                    
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
