import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TestApiCalls {
    public static void main(String[] args) {
        String apiKey = "efc9ed5d3832c3fd8a42025f5883e23173f02907";
        String authHeader = "Token " + apiKey; // der "kopf" unserer api-request lautet jetzt "Basic <base64code>". das "Basic" und das leerzeichen vor dem base64 string sind dabei wichtig

        HttpClient client = HttpClient.newHttpClient(); // objektorientiert einen neuen client für http requests erzeugen

        HttpRequest allDrones = HttpRequest.newBuilder() // erste http-get-request für alle drones vorbereiten
                .uri(URI.create("http://dronesim.facets-labs.com/api/drones/"))
                .header("Authorization", authHeader)
                .GET()
                .build();

        HttpRequest oneDrone = HttpRequest.newBuilder() //zweite http-get-request für eine drone vorbereiten
                .uri(URI.create("http://dronesim.facets-labs.com/api/drones/62/"))
                .header("Authorization", authHeader)
                .GET()
                .build();
        try { // hier probieren wir die abfragen
            System.out.println("Sending first request in 5 sec");
            Thread.sleep(5000);
            System.out.println("Send All Drones Request");
            HttpResponse<String> allDronesResponse = client.send(allDrones, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + allDronesResponse.statusCode());
            System.out.println("Response: " + allDronesResponse.body());
            System.out.println("Wait 5 sec...");
            Thread.sleep(5000);
            System.out.println("Send One Drone Request");
            HttpResponse<String> oneDroneResponse = client.send(oneDrone, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + oneDroneResponse.statusCode());
            System.out.println("Response: " + oneDroneResponse.body());
        } catch (Exception e) { // hier fangen wir fehler ab
            e.printStackTrace();
        }
    }
}
