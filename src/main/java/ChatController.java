import javax.swing.*;

class ChatController {
    private ChatGUI gui;

    public ChatController(ChatGUI gui) {
        this.gui = gui;
        addActionListeners();
        requestUserName();
    }

    private void requestUserName() {
        String userName;
        do {
            userName = JOptionPane.showInputDialog(gui, "Enter your name:", "User Login", JOptionPane.PLAIN_MESSAGE);
        } while (userName == null || userName.trim().isEmpty());
        gui.setUserName(userName);
        gui.addUser(userName);
    }

    private void addActionListeners() {
        gui.getSendButton().addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = gui.getTextField().getText();
        if (!message.trim().isEmpty()) {
            gui.addMessage(gui.getUserName() + ": " + message);
            gui.getTextField().setText("");
        }
    }
}