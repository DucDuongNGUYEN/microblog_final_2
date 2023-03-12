import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class PublisherClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static MicroblogDatabase database;


    static {
        try {
            database = new MicroblogDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            System.out.print("Enter a message: ");
            String message = scanner.nextLine();

            // Send the message to the server via a PUBLISH request
            output.println("PUBLISH author:@" + username);
            database.insertAuthor("@"+username);
            database.insertContent(username,message);
            output.println(message);

            // Wait for the server's response
            String response = input.readLine();
            if (response.equals("OK")) {
                System.out.println("Message published successfully!");
            } else {
                System.out.println("Error: " + response);
            }
        }
    }
}
