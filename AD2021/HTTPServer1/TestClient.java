package AD2021.HTTPServer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TestClient {
    static final int EXERCISE_NUM = 2; //exercise questions.

    public static void main(String[] args) throws IOException {

        Socket s = new Socket(InetAddress.getByName("localhost"), 8080);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String response;
        switch (EXERCISE_NUM) {
            //To test 501 not implemented
            case 1:
                sendRequest(pw, "abc", false, false);
                break;
            //To test index file
            case 2:
                sendRequest(pw, "get", false, false);
                break;
            //To test 404 file not found
            case 3:
                sendRequest(pw, "get", true, false);
                break;
            //To test head method with index file
            case 4:
                sendRequest(pw, "head", false, false);
                break;
            //To test head method with file not found
            case 5:
                sendRequest(pw, "head", true, false);
                break;
            //To test post method with index file
            case 6:
                sendRequest(pw, "post", false, true);
                break;
            //To test user validation
            case 7:
                sendRequestUserValidation(pw, "post", true, true);
                break;
            //To test poker distribution
            case 8:
                sendRequestPokerDistribution(pw, "post", true, true);
                break;
            default:
                System.out.println("Wrong Exercise!!");
        }


        while ((response = br.readLine()) != null) System.out.println(response);


        pw.close();
        br.close();
    }

    private static void sendRequest(PrintWriter pw, String method, Boolean requestFileFlag, Boolean bodyFlag) {
        pw.print(method + " /");
        if (requestFileFlag) {
            pw.print("xyz");
        }
        pw.print(" HTTP/1.1\r\n");
        pw.print("Host: localhost\r\n\r\n");

        if (bodyFlag) {
            pw.print("username = Smith\r\n");
        }
        pw.flush();
    }

    private static void sendRequestPokerDistribution(PrintWriter pw, String method, Boolean requestFileFlag, Boolean bodyFlag) {
        pw.print(method + " /");
        if (requestFileFlag) {
            pw.print("PokerDistribution/");
        }
        pw.print(" HTTP/1.1\r\n");
        pw.print("Host: localhost\r\n\r\n");

        if (bodyFlag) {
            pw.print("username = playerA\r\n");
        }
        pw.flush();
    }
    private static void sendRequestUserValidation(PrintWriter pw, String method, Boolean requestFileFlag, Boolean bodyFlag) {
        pw.print(method + " /");
        if (requestFileFlag) {
            pw.print("UserValidation/");
        }
        pw.print(" HTTP/1.1\r\n");
        pw.print("Host: localhost\r\n\r\n");

        if (bodyFlag) {
            StringBuilder uvb = new StringBuilder();
            uvb.append("8");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("Julia");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("Samantha");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("Samantha_21");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("1Samantha");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("Samantha?10_2A");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("JuliaZ007");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("Julia@007");
            uvb.append(System.getProperty("line.separator"));
            uvb.append("_Julia007");
            uvb.append(System.getProperty("line.separator"));


            pw.print(uvb.toString());
            System.out.println(uvb.toString());
        }
        pw.flush();
    }
}
