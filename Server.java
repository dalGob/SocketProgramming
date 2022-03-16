import java.io.BufferedReader;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.PrintWriter;

public class Server {

    public static void main(String[] args) {
        Socket clientSocket;
        ServerSocket serverSocket;
        BufferedReader reader;
        PrintWriter writer;
        InputStreamReader input;
        OutputStreamWriter output;

        System.out.print("Please input the port number: ");
        int port = Integer.parseInt(System.console().readLine());

        try {

            serverSocket = new ServerSocket(port);
            while (true) {

                clientSocket = serverSocket.accept();

                input = new InputStreamReader(clientSocket.getInputStream());
                output = new OutputStreamWriter(clientSocket.getOutputStream());

                reader = new BufferedReader(input);
                writer = new PrintWriter(output);

                while (true) {
                    String messageFromClient = reader.readLine();

                    System.out.println("Client: " + messageFromClient);

                    writer.write("Message received\n");
                    writer.flush();

                    if (messageFromClient.equalsIgnoreCase("exit")) {
                        System.out.println("Server shutting down");
                        break;
                    }
                }

                clientSocket.close();
                input.close();
                output.close();
                reader.close();
                writer.close();

                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }

    }
}