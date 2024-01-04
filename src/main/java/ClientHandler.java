import java.io.*;
import java.net.*;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                for (ClientHandler client : clients) {
                    client.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Ein Client hat die Verbindung getrennt.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
