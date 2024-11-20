import datamodels.LocationData;
import services.LocationService;

public class Main {
    public static void main(String[] args) {

        LocationService locationService = new LocationService();

        try {
            LocationData locationData = locationService.getLocationData();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}