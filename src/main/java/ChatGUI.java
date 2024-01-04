import javax.swing.*;
import java.awt.*;

class ChatGUI extends JFrame {
    private JButton sendButton;
    private JTextField textField;
    private JTextArea chatArea;
    private DefaultListModel<String> userListModel;
    private JList<String> userList;
    private String userName;

    public ChatGUI() {
        setTitle("Chat Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setupMenubar();
        setupUserList();
        setupChatArea();
        setupInputPanel();
    }

    private void setupMenubar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.add(saveMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
        menu.add(exitMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void setupUserList() {
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setPreferredSize(new Dimension(100, 0));
        add(userListScrollPane, BorderLayout.WEST);
    }


    private void setupChatArea() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
    }

    private void setupInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        textField = new JTextField();
        sendButton = new JButton("Send");
        panel.add(textField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
    }

    public void addUser(String userName) {
        userListModel.addElement(userName);
    }

    public void addMessage(String message) {
        chatArea.append(message + "\n");
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getTextField() {
        return textField;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}