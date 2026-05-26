package org.example.rdf4j_getting_started;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;

public class directions {

    static String apiKey = "AIzaSyC2hUQB0pFED4a4g2r26ER4IzAEb2MEOew"; 

    public static void run(Scanner sc) throws Exception {
        System.out.println("Enter the two addresses you want to find the distance between");
        String originAddress = sc.nextLine();
        String destinationAddress = sc.nextLine();

        double[] origin = geocode(originAddress);
        double[] destination = geocode(destinationAddress);

        getRoute(origin, destination);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        run(sc);
        sc.close();
    }

    // ---------------- GEOCODING ----------------
    public static double[] geocode(String address) throws Exception {

        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);

        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + encodedAddress
                + "&key=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());

        String status = json.optString("status");

        System.out.println("Geocode status for " + address + ": " + status);
        System.out.println("Raw response: " + response.body());

        // SAFETY CHECK (prevents JSONArray[0] crash)
        if (!status.equals("OK")) {
            throw new RuntimeException(
                    "Geocoding failed for: " + address + " | status: " + status
            );
        }

        JSONObject location = json
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        return new double[]{
                location.getDouble("lat"),
                location.getDouble("lng")
        };
    }

    // ---------------- ROUTES API ----------------
    public static void getRoute(double[] origin, double[] destination) throws Exception {

        String url = "https://routes.googleapis.com/directions/v2:computeRoutes";

        String body = "{"
                + "\"origin\": {\"location\": {\"latLng\": {"
                + "\"latitude\": " + origin[0] + ","
                + "\"longitude\": " + origin[1]
                + "}}},"
                + "\"destination\": {\"location\": {\"latLng\": {"
                + "\"latitude\": " + destination[0] + ","
                + "\"longitude\": " + destination[1]
                + "}}},"
                + "\"travelMode\": \"DRIVE\","
                + "\"routingPreference\": \"TRAFFIC_AWARE\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("X-Goog-Api-Key", apiKey)
                .header("X-Goog-FieldMask", "routes.duration,routes.distanceMeters")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Route Response:");
        String meters = response.body().substring(response.body().indexOf("distanceMeters")+17,response.body().indexOf(","));
        double miles = Integer.parseInt(meters)*0.00062137;
        System.out.println(meters + " meters" +" or (" + miles+ " miles)");
        String duration = response.body().substring(response.body().indexOf("duration")+12,response.body().indexOf("duration")+15);
        System.out.println(duration);
        int dur = Integer.parseInt(duration);
        if (Integer.parseInt(meters)>30000) {
            System.out.println(dur/60 + " hours and " + dur%60 + " minutes");
        }
        else {
        System.out.println(dur/60 + " minutes and " + dur%60 + " seconds");
        
        }
    }
}