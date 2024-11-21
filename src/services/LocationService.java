package services;

import datamodels.LocationData;
import org.json.JSONObject;
import utils.RequestResponseHandler;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocationService {

    public String getPublicIPAddress() throws Exception {
        String ipServiceUrl = "https://api.ipify.org?format=json";
        String response = RequestResponseHandler.sendGetRequest(ipServiceUrl);

        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getString("ip");
    }

    public LocationData getLocationData() throws Exception {
        String publicIp = getPublicIPAddress();
        String locationServiceUrl = "https://ipapi.co/" + publicIp + "/json/";
        String response = RequestResponseHandler.sendGetRequest(locationServiceUrl);

        JSONObject jsonObject = new JSONObject(response);
        String city = jsonObject.getString("city");
        String country = jsonObject.getString("country");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        return new LocationData(city, country, latitude, longitude);
    }


}
