import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        System.out.print("Please input the port number: ");
        int port = keyboard.nextInt();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Listening");

        String[] choices = {"Silence", "Betray"};

        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while (true) {
                int number = (int)(Math.random() * 2);

                String msgFromClient = reader.readLine();
                System.out.println("Client: " + msgFromClient);
                System.out.println("Server: " + choices[number]);
                String msgFromServer;

                if(choices[number].equals("Silence")) {
                    if(msgFromClient.equals("Silence")) {
                        msgFromServer = "Prisoner B's choice: Silence, Prisoner A's sentence: 1 year in prison, Prisoner B's sentence: " +
                                "1 year in prison";
                    } else {
                        msgFromServer = "Prisoner B's choice: Silence, Prisoner A's sentence: Freedom, Prisoner B's sentence: " +
                                "3 year in prison";
                    }
                } else {
                    if (msgFromClient.equals("Silence")) {
                        msgFromServer = "Prisoner B's choice: Betray, Prisoner A's sentence: 3 year in prison, Prisoner B's sentence: " +
                                "Freedom";
                    } else {
                        msgFromServer = "Prisoner B's choice: Betray, Prisoner A's sentence: 2 year in prison, Prisoner B's sentence: " +
                                "2 year in prison";
                    }
                }

                System.out.println("Outcome: " + msgFromServer);
                writer.println(msgFromServer);
                writer.flush();

                if(msgFromClient.equalsIgnoreCase("quit")) {
                    break;
                }
            }

            clientSocket.close();
            reader.close();
            writer.close();
        }
    }
}