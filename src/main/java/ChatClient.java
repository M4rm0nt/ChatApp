import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ChatClient {
    private String serverAddress;
    private int serverPort;
    private ChatGUI gui;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    public ChatClient(String serverAddress, int serverPort, ChatGUI gui) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.gui = gui;
    }

    public void connect() {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            running = true;
            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null && running) {
                String finalMessage = message;
                SwingUtilities.invokeLater(() -> gui.addMessage(finalMessage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void closeConnections() {
        try {
            if (socket != null) socket.close();
            if (out != null) out.close();
            if (in != null) in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        closeConnections();
    }
}
