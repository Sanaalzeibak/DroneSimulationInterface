import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class BetaForm {
    private JButton button1;
    private JLabel responseLabel;
    private JPanel mainPanel;

    public BetaForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responseLabel.setText("Sending request...");
                new Thread(() -> {
                    try {
                        String response = performApiCall();
                        SwingUtilities.invokeLater(() -> responseLabel.setText("<html>" + response + "</html>"));
                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() -> responseLabel.setText("Error: " + ex.getMessage()));
                    }
                }).start();
            }
        });
    }

    private String performApiCall() throws Exception {
        String username = "group7";
        String password = "0R8PHcpsJYIjNZ";

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest allDrones = HttpRequest.newBuilder()
                .uri(URI.create("http://dronesim.facets-labs.com/api/drones/"))
                .header("Authorization", authHeader)
                .GET()
                .build();

        HttpResponse<String> response = client.send(allDrones, HttpResponse.BodyHandlers.ofString());
        return "Status: " + response.statusCode() + "<br>Response: " + response.body();
    }

    public static void main(String[] args) {
        // JFrame erstellen und anzeigen
        JFrame frame = new JFrame("BetaForm");
        frame.setContentPane(new BetaForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
