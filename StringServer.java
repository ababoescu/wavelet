import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    ArrayList <String> myStringList = new ArrayList<>();
    String s = new String();
    
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Need to add this to your url code: /add-message?s=";
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {    
                    s = parameters[1];
                    myStringList.add(s);
                    System.out.println(myStringList);

                    String tempConcatString = myStringList.get(0);
                    tempConcatString = tempConcatString + "\n";
                    for(int i = 1; i < myStringList.size(); i++){
                        tempConcatString = tempConcatString + myStringList.get(i);
                        tempConcatString = tempConcatString + "\n";
                    }
                    return tempConcatString; 
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
