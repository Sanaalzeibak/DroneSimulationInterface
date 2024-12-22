package UrlConnection;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UrlConnection {
    public static final String BASE_URL = "http://dronesim.facets-labs.com/api/";
    public static final String TOKEN = "efc9ed5d3832c3fd8a42025f5883e23173f02907";

    public static String Get(String endpoint) {
        return Get(endpoint, 10 ,0);
    }

    public static String Get(String endpoint, int limit, int offset) {
        String charset = StandardCharsets.UTF_8.name(); // Zeichensatz (UTF-8)

        // Query-Parameter codieren und zur URL hinzufügen
        String urlWithParams = BASE_URL + endpoint + "?limit=" + URLEncoder.encode(String.valueOf(limit), StandardCharsets.UTF_8)
                + "&offset=" + URLEncoder.encode(String.valueOf(offset), StandardCharsets.UTF_8);

        try {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Authorization", "Token " + TOKEN)
                .uri(URI.create(urlWithParams)) // URL mit Parametern
                .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                // Success: Return the response body
                System.out.println("Response code: " + statusCode);
                System.out.println("Response body: " + response.body());
                return response.body();
            } else if (statusCode >= 400 && statusCode < 500) {
                // Client error
                System.err.println("Client Error: " + statusCode + " - " + response.body());
            } else if (statusCode >= 500) {
                // Server error
                System.err.println("Server Error: " + statusCode + " - " + response.body());
            } else {
                // Unexpected status code
                System.err.println("Unexpected Response Code: " + statusCode + " - " + response.body());
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
