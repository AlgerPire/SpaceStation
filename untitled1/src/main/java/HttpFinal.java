import com.google.gson.Gson;
import com.squareup.okhttp.*;
import entities.Info;
import model.LocationFinal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.logging.Level;

import java.sql.Time;
import java.time.LocalTime;

public class HttpFinal {
    static Session session;
    static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {

        // Turn Hibernate logging off
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Info.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static final String BASE_URL2 = "https://api.bigdatacloud.net/data/reverse-geocode-client?";

    // location method
    public String addLocationInDatabase(){
        try {
            // Create http client
            OkHttpClient client = new OkHttpClient();

            // Set URL and parameters
            HttpUrl.Builder urlBuilder
                    = HttpUrl.parse(BASE_URL2).newBuilder();

            HttpTest httpTest=new HttpTest();
            String latitude=httpTest.getInfo().getLatitude();
            String longtitude=httpTest.getInfo().getLongitude();

            // Set query parameters
            urlBuilder.addQueryParameter("latitude", latitude);
            urlBuilder.addQueryParameter("longitude", longtitude);

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
            LocationFinal getLocationFinal = gson.fromJson(sResponse, LocationFinal.class);


            Info info = null;
            LocationFinal locationFinal = new LocationFinal();
            LocalTime timeNow = LocalTime.now().withNano(0);
            try {
                session = buildSessionFactory().openSession();
                session.beginTransaction();
                info = new Info();
                info.setDivision(getLocationFinal.getPrincipalSubdivision());
                info.setContinent(getLocationFinal.getContinent());
                info.setCity(getLocationFinal.getCity());
                info.setLocality(getLocationFinal.getLocality());
                info.setCounty(getLocationFinal.getCountryName());
                info.setOra(Time.valueOf(timeNow));
                session.save(info);
                session.getTransaction().commit();
                System.out.println("Thank you for waiting. The position is in database now, folks!");

            }
            catch (Exception e) {
                e.printStackTrace();


            } finally {
                if (session != null) {
                    session.close();
                }
            }


        } catch (Exception e) {
            System.out.println("* Something went wrong with API *");
        }

        return null;

    }



}
