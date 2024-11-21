import datamodels.LocationData;
import services.LocationService;

public class Main {
    public static void main(String[] args) {

        LocationService locationService = new LocationService();

        try {
            LocationData locationData = locationService.getLocationData();
            System.out.println("City: " + locationData.getCity());
            System.out.println("Country: " + locationData.getCountry());
            System.out.println("Latitude: " + locationData.getLatitude());
            System.out.println("Longitude: " + locationData.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}