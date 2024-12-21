import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {
    private int count;
    private String next;
    private String previous;
    private List<Drone> results;

    // Getter und Setter
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Drone> getResults() {
        return results;
    }

    public void setResults(List<Drone> results) {
        this.results = results;
    }

    // Methode zum Erstellen eines ApiResponse-Objekts aus einem JSON-String
    public static APIResponse fromJson(String jsonResponse) {
        APIResponse apiResponse = new APIResponse();

        // JSON-Objekt erstellen
        JSONObject jsonObject = new JSONObject(jsonResponse);
        apiResponse.setCount(jsonObject.getInt("count"));
        apiResponse.setNext(jsonObject.getString("next"));
        apiResponse.setPrevious(jsonObject.optString("previous", null)); // Kann null sein

        // Ergebnisse (Drone-Liste) einlesen
        JSONArray resultsArray = jsonObject.getJSONArray("results");
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject droneJson = resultsArray.getJSONObject(i);
            Drone drone = Drone.fromJson(droneJson);
            drones.add(drone);
        }
        apiResponse.setResults(drones);

        return apiResponse;
    }
}