package services;

import datamodels.LocationData;
import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocationService {

    private final HttpClient httpclient;

    public LocationService() {
        this.httpclient = HttpClient.newHttpClient();
    }

    public String getPublicIPAddress() throws Exception {
        String ipServiceUrl = "https://api.ipify.org?format=json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(ipServiceUrl))
                .GET()
                .build();

        HttpResponse<String>response=httpclient.send(request,HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()==200)
        {
            JSONObject jsonObject = new JSONObject(response.body());
            return jsonObject.getString("ip");
        }
        else {
            throw new Exception("Failed to get public IP address");
        }
    }

    public LocationData getLocationData() throws Exception {
        String locationServiceUrl = "https://ipapi.co/" + getPublicIPAddress() + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI(locationServiceUrl))
                .GET()
                .build();

        HttpResponse<String>response=httpclient.send(request,HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()==200)
        {
            JSONObject jsonObject = new JSONObject(response.body());
            //{"utc_offset":"+0600","country":"BD","country_tld":".bd","country_population":161356039,"city":"Tongi","timezone":"Asia/Dhaka","latitude":23.8667,"network":"103.82.172.0/22","country_area":144000,"country_code_iso3":"BGD","country_name":"Bangladesh","currency":"BDT","country_capital":"Dhaka","country_calling_code":"+880","longitude":90.4,"currency_name":"Taka","languages":"bn-BD,en","org":"Islamic University of Technology IUT","ip":"103.82.173.150","continent_code":"AS","version":"IPv4","country_code":"BD","in_eu":false,"postal":"1230","region":"Dhaka Division","asn":"AS136164","region_code":"C"}
            String city = jsonObject.getString("city");
            String country = jsonObject.getString("country");
            double latitude = jsonObject.getDouble("latitude");
            double longitude = jsonObject.getDouble("longitude");

            return new LocationData(city, country, latitude, longitude);

        }

        else {
            throw new Exception("Failed to get location data");
        }
    }


}
