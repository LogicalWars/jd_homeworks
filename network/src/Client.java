import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", Server.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("Ivan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
