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

        System.out.println("Please input the port number");
        int port = Integer.parseInt(System.console().readLine());

        try {
            while (true) {
                serverSocket = new ServerSocket(port);
                clientSocket = serverSocket.accept();

                input = new InputStreamReader(clientSocket.getInputStream());
                output = new OutputStreamWriter(clientSocket.getOutputStream());

                reader = new BufferedReader(input);
                writer = new PrintWriter(output);

                while (true) {
                    String line = reader.readLine();

                    System.out.println("Client: " + line);

                    writer.write("Message received\n");
                    writer.flush();

                    if (line.equalsIgnoreCase("exit")) {
                        break;
                    }

                }

                clientSocket.close();
                input.close();
                output.close();
                reader.close();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}