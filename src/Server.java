import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class Server {

    private static final int PORT = 12345;
    private static long messageId = 1;
    private static MicroblogDatabase database;

    static {
        try {
            database = new MicroblogDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Microblog server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String request = in.readLine();
            if (request.startsWith("PUBLISH")) {
                String message = in.readLine();
                publishMessage(message);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("OK");
            } else {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("ERROR: unknown command");
            }
            clientSocket.close();
        }
    }

    private static void publishMessage(String message) throws SQLException {
        System.out.println("PUBLISH author:@" + database.getAuthor(messageId));
        System.out.println("Content : " + message);
        messageId++;
    }
}
