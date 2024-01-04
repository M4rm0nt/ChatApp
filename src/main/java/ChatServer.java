import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private int port;
    private List<ClientHandler> clients;
    private boolean isRunning;

    public ChatServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.isRunning = true;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server gestartet. Warte auf Verbindungen...");

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler clientThread = new ClientHandler(clientSocket, clients);
                    clients.add(clientThread);
                    clientThread.start();
                } catch (IOException e) {
                    if (isRunning) {
                        System.out.println("Fehler beim Akzeptieren der Verbindung: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server konnte nicht gestartet werden: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(12345);
        server.startServer();
    }

}
