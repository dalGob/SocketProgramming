import java.io.BufferedReader;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

public class Server {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listen() {

        try {
            System.out.println("Server is listening on port: " + serverSocket.getLocalPort());
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Please input the port number");
        int port = Integer.parseInt(System.console().readLine());
        Server server = new Server(port);
        server.listen();
    }
}