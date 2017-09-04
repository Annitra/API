package Requests;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 20.08.2017.
 */
public class RequestsTools {


    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private static String[] getData(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();

        String[] responseData = new String[2];
        responseData[0] = response.getEntity().toString();
        responseData[1] = entity != null ? EntityUtils.toString(entity) : "No response data.";
        return responseData;
    }

    public static String[] getRequest(String URL) throws IOException {
        HttpGet request = new HttpGet(URL);
        return getData(httpclient.execute(request));
    }

    public static String[] putRequest(String URL, String data) throws IOException {
        HttpPut request = new HttpPut(URL);
        request.setEntity(new StringEntity(data, "UTF-8"));
        return getData(httpclient.execute(request));
    }



    public static String[] deleteRequest(String URL, String data) throws IOException {
        HttpDelete request = new HttpDelete(URL);
        request.setEntity(new StringEntity(data, "UTF-8"));
        return getData(httpclient.execute(request));
    }
    public static String[] postRequest(String URL, String data) throws IOException {
        HttpPost request = new HttpPost(URL);
        request.setEntity(new StringEntity(data, "UTF-8"));
        return getData(httpclient.execute(request));
    }

    public static String[] getUserList()throws IOException{
        String[] list = getRequest("http://soft.it-hillel.com.ua:3000/api/users");
        return list;
    }
    public static String getUserInfo(String id) throws IOException {
       String data =getRequest ("http://soft.it-hillel.com.ua:3000/api/users")[0];
    //    String data =getUserList()[0];
        Pattern findUserInfo = Pattern.compile("\\{[^\\{\\}]*\"id\":\"" + id + "\"[^\\{\\}]*\\}");
        Matcher m = findUserInfo.matcher(data);
        if (m.find()) {
            return m.group();
        } else {
            return "No data found.";
        }
    }


    public static boolean finNewID (String data) {
        Pattern findID = Pattern.compile("\"id\":\"([0-9]+)");
        Matcher m = findID.matcher(data);
        return  m.find();
    }


    public static void checkContentType(String headers) {
        Assert.assertTrue(headers.contains("Content-Type: application/json"));
    }
}

