import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Socket clientSocket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        System.out.print("Please input the ip address: ");
        String ip = keyboard.nextLine();
        System.out.print("Please input the port number: ");
        int port = keyboard.nextInt();
        keyboard.nextLine();

        try {
            clientSocket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String wish = "";

        while(true) {
            String message;
            do {
                message = getInput("Please enter either Silence or Betray: ");
            } while (!message.equals("Silence") && !message.equals("Betray"));

            if (writer != null) {
                writer.println(message);
            }

            if (reader != null) {
                System.out.println(reader.readLine());
            }

            do  {
                wish = getInput("Try again or quit: ");
            } while (!wish.equalsIgnoreCase("Try again") && !wish.equalsIgnoreCase("quit"));

            if(wish.equalsIgnoreCase("quit")) {
                writer.println("quit");
                break;
            }
        }

        clientSocket.close();
        writer.close();
        reader.close();
    }

    public static String getInput(String display) {
        System.out.print(display);
        String input = keyboard.nextLine();
        return input.trim();
    }
}