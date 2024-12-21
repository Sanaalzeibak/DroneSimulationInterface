import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestApiCalls {
    public static void main(String[] args) {
        String apiKey = "efc9ed5d3832c3fd8a42025f5883e23173f02907";
        String authHeader = "Token " + apiKey; //geändert zu token auth

        HttpClient client = HttpClient.newHttpClient(); // objektorientiert einen neuen client für http requests erzeugen

        HttpRequest allDrones = HttpRequest.newBuilder() // erste http-get-request für alle drones vorbereiten
                .uri(URI.create("http://dronesim.facets-labs.com/api/drones/"))
                .header("Authorization", authHeader)
                .GET()
                .build();

        HttpRequest oneDrone = HttpRequest.newBuilder() //zweite http-get-request für eine drone vorbereiten
                .uri(URI.create("http://dronesim.facets-labs.com/api/drones/91/"))
                .header("Authorization", authHeader)
                .GET()
                .build();

        HttpRequest droneDynamics = HttpRequest.newBuilder() //zweite http-get-request für eine drone vorbereiten
                .uri(URI.create("http://dronesim.facets-labs.com/api/dronedynamics/91/"))
                .header("Authorization", authHeader)
                .GET()
                .build();

        try { // hier probieren wir die abfragen

            System.out.println("Sending first request in 5 sec");
            Thread.sleep(5000);
            System.out.println("Send All Drones Request");
            HttpResponse<String> allDronesResponse = client.send(allDrones, HttpResponse.BodyHandlers.ofString());


            /*
            System.out.println("Status: " + allDronesResponse.statusCode());
            System.out.println("Response: " + allDronesResponse.body());
            System.out.println("Wait 5 sec...");
            Thread.sleep(5000);
            System.out.println("Send One Drone Request");
            HttpResponse<String> oneDroneResponse = client.send(oneDrone, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + oneDroneResponse.statusCode());
            System.out.println("Response: " + oneDroneResponse.body());
            System.out.println("Wait 5 sec...");
            Thread.sleep(5000);
            System.out.println("Send Drone Dynamics Request");
            HttpResponse<String> droneDynamicRequest = client.send(droneDynamics, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + droneDynamicRequest.statusCode());
            System.out.println("Response: " + droneDynamicRequest.body());
            */


            APIResponse apiResponse = APIResponse.fromJson(allDronesResponse.body());
            //api response ist eine klasse passend für json objekte, count etc kann direkt verarbeitet werden, drones werden innerhalb des objekts belegt
            System.out.println("Anzahl der Dronen: " + apiResponse.getCount());

            //drones sind elemente von api responses, hier geben wir alle aus
            for (Drone drone : apiResponse.getResults()) {
                System.out.println("Drone ID: " + drone.getId());
                System.out.println("Serialnummer: " + drone.getSerialnumber());
            }
            System.out.println("_______________________________________");

            //auf einzelne dronen kann über das apiresponse-objekt zugegriffen werden
            Drone testDrone0 = apiResponse.getResults().get(0);
            Drone testDrone1 = apiResponse.getResults().get(1);
            Drone testDrone2 = apiResponse.getResults().get(2);

            //jede drone hat eigene getter (und setter wg. konvention) zum abrufen von daten
            System.out.println(testDrone0.getSerialnumber());
            System.out.println(testDrone1.getCarriageWeight());
            System.out.println(testDrone2.getCreated());

        } catch (Exception e) { // hier fangen wir fehler ab
            e.printStackTrace();
        }
    }
}
