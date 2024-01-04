import javax.swing.*;

public class ChatApp {
    public static void main(String[] args) {

        new Thread(() -> {
            ChatServer server = new ChatServer(12345);
            server.startServer();
        }).start();


        SwingUtilities.invokeLater(() -> {
            ChatGUI gui = new ChatGUI();
            ChatClient client = new ChatClient("localhost", 12345, gui);
            ChatController controller = new ChatController(gui, client);
            gui.setVisible(true);
        });
    }
}
