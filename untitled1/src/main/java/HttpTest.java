import com.google.gson.*;
import com.squareup.okhttp.*;
import lombok.*;
import model.LocationInformation;
import model.SpecifiedInfo;

import javax.xml.stream.Location;


@Data
@NoArgsConstructor

public class HttpTest {
    public static final String BASE_URL = "http://api.open-notify.org/iss-now.json";

    //base url per location


    public SpecifiedInfo getInfo() {
        try {
            // Create http client
            OkHttpClient client = new OkHttpClient();

            // Set URL and parameters
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(BASE_URL).newBuilder();

            String url = urlBuilder.build().toString();

            // Build request and execute
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();

            // Convert response to String
            String sResponse = response.body().string();

            // Convert String to our class model
            Gson gson = new Gson();
            LocationInformation getLocationInformation = gson.fromJson(sResponse, LocationInformation.class);


            // Return class model
            return getLocationInformation.getIss_position();
        } catch (Exception e) {
            System.out.println("* Something went wrong with API *");
        }

        return null;
    }






}
