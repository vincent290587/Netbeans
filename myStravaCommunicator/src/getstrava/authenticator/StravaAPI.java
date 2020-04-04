package getstrava.authenticator;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StravaAPI {

    public static long getCurrentAtheleteID(String bearer) {
        JSONObject obj = httpGetRequest(
                "https://www.strava.com/api/v3/athlete", bearer);

        return (long) obj.get("id");
    }

    public static long getActivity(String bearer, long id) {
        JSONObject obj = httpGetRequest(
                "https://www.strava.com/api/v3/activities/" + id, bearer);

        return (long) obj.get("id");
    }

    public static long uploadActivity(String bearer, String fileName) {

        JSONObject jsonObj = null;

        HttpHost proxy = new HttpHost("proxy.fr", 8050, "http");

        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        credsProvider.setCredentials(
                new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials("gollev", "password")
        );

        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        CloseableHttpClient httpClient = HttpClients.custom()
                //.setProxy(proxy)
                //.setRoutePlanner(routePlanner)
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        HttpPost httpPost = new HttpPost(
                "https://www.strava.com/api/v3/uploads");
        httpPost.addHeader("Authorization", "Bearer " + bearer);
        httpPost.setHeader("enctype", "multipart/form-data");


        FileBody bin = new FileBody(new File(fileName));

        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();

        multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            multipartEntity.addPart("activity_type", new StringBody("ride"));
            multipartEntity.addPart("data_type", new StringBody("gpx"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StravaAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        multipartEntity.addPart("file", bin);

        httpPost.setEntity(multipartEntity.build());


        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);

            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                // EntityUtils to get the response content
                String content = EntityUtils.toString(respEntity);
                System.out.println(content);
                JSONParser jsonParser = new JSONParser();
                jsonObj = (JSONObject) jsonParser.parse(content);
            }
        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return (long) jsonObj.get("id");

    }

    // http://stackoverflow.com/questions/20370095/strava-v3-api-upload-using-scribe-on-google-app-engine
    /**
     * Sends a REST type request to the server and returns the response as a
     * JSON object tree
     *
     * @param endPoint
     * @param bearer
     * @return
     */
    public static JSONObject httpGetRequest(String endPoint, String bearer) {
        JSONObject jsonObj = null;

        try {
            HttpHost proxy = new HttpHost("coo-surf.cst.cnes.fr", 8050, "http");

            CredentialsProvider credsProvider = new BasicCredentialsProvider();

            credsProvider.setCredentials(
                    new AuthScope(proxy.getHostName(), proxy.getPort()),
                    new UsernamePasswordCredentials("gollev", "Z@pdos22!")
            );

            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

            CloseableHttpClient client = HttpClients.custom()
                    //.setProxy(proxy)
                    //.setRoutePlanner(routePlanner)
                    .setDefaultCredentialsProvider(credsProvider)
                    .build();

            HttpGet request = new HttpGet(endPoint);

            // add request header
            request.addHeader("Authorization", "Bearer " + bearer);

            HttpResponse response = client.execute(request);
            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                // EntityUtils to get the response content
                String content = EntityUtils.toString(respEntity);
                System.out.println(content);
                JSONParser jsonParser = new JSONParser();
                jsonObj = (JSONObject) jsonParser.parse(content);
            }
        } catch (IOException | ParseException e) {
            System.err.println(e.toString());
        }
        return jsonObj;
    }
}
