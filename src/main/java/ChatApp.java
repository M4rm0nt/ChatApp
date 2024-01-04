import javax.swing.*;

public class ChatApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatGUI gui = new ChatGUI();
            new ChatController(gui);
            gui.setVisible(true);
        });
    }
}