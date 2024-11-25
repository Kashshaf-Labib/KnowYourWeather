package proxyservice;

import behaviours.IWeatherService;
import datamodels.WeatherData;
import serviceadapters.LocationServiceAdapter;
import utils.WeatherCacheManager;

public class WeatherServiceProxy implements IWeatherService {
    private IWeatherService weatherService;
    private WeatherCacheManager cacheManager;

    public WeatherServiceProxy(IWeatherService weatherService)
    {
        this.weatherService=weatherService;
        this.cacheManager=new WeatherCacheManager();
    }



    @Override
    public WeatherData getWeatherDataByCity(String city) throws Exception {
        WeatherData weatherData=cacheManager.getByCity(city);
        if(weatherData!=null)
        {
            return weatherData;
        }
        weatherData=weatherService.getWeatherDataByCity(city);
        cacheManager.putByCity(city,weatherData);
        return weatherData;
    }

    @Override
    public WeatherData getWeatherDataByIP() throws Exception {
        LocationServiceAdapter location = new LocationServiceAdapter();

        double latitude = location.getLocationData().getLatitude();
        double longitude = location.getLocationData().getLongitude();

        WeatherData weatherData=cacheManager.getByCoordinates(latitude,longitude);
        if(weatherData!=null)
        {
            return weatherData;
        }
        weatherData=weatherService.getWeatherDataByIP();
        cacheManager.putByCoordinates(latitude,longitude,weatherData);
        return weatherData;
    }
}
