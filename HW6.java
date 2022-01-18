/**
 * JAVA 2 Homework6
 *
 * @author Oksana Ilyakova
 * @version 18.01.2022
 */
public class HW6 {

    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "V1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day"
    private static final String API_KEY = "RZn4AO9JYqiARlMgGgxqYiMn3B0A8TaQ";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMappernt = new ObjectMapper();

    @Override
    public void getWeather(String selectedCity, Period period) {
        swith(period){
            case ONE_DAY:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARA, API_KEY)
                        .build();
                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastsResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = ForecastsResponse.body().string();

                System.out.println(weatherResponse);

                break;
        }
    }

     public String detectCityKey(String selectedCity) {
         //http://dataservice.accuweather.com/locations/v1/adminareas/
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(LOCATIONS)
                        .addPathSegment(VERSION)
                        .addPathSegment(CITIES)
                        .addPathSegment(AUTOCOMPLETE)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(name:"q", selectedCity)
                        .build();

         Request request = new Request.Builder()
                 .url(httpUrl)
                 .get()
                 .addHeader(name:"accept", value"application/json")
                 .build();

         Response response  = okHttpClient.newCall(request).execute();
         String responseCity = response.body().string();

         System.out.println(weatherResponse);
         return responseCity;
            }

}
