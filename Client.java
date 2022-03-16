import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    private void startConnection(int port) {

        try {
            clientSocket = new Socket("localhost", port);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void sendMessage(String message) {
        try {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println(message);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        System.out.print("Please input the port number: ");
        int port = Integer.parseInt(System.console().readLine());

        System.out.println("CLIENT");
        Client client = new Client();
        client.startConnection(port);
        client.sendMessage("Hello");

    }
}