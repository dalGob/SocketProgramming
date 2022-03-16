import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {

    public static void main(String[] args) {

        Socket clientSocket;
        BufferedReader reader;
        PrintWriter writer;
        System.out.print("Please input the port number: ");
        int port = Integer.parseInt(System.console().readLine());

        try {
            while (true) {
                clientSocket = new Socket("localhost", port);
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                while (true) {
                    String message = System.console().readLine();
                    writer.write(message + "\n");
                    writer.flush();

                    System.out.println("Server Response: " + reader.readLine());

                    if (message.equalsIgnoreCase("exit")) {
                        System.out.println("Breaking Inner loop on client side");
                        break;
                    }

                }

                clientSocket.close();
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

    public static String getInput() {
        System.out.print("Silent or Betray: ");
        String input = System.console().readLine();
        return input.trim();
    }
}