import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.08.2017.
 */
public class HttpClientRequests extends Methods {

    public String put (String urlPut, String bodyPut) throws Exception{
        // String urlPut=urlPost+getPostId();
        org.apache.http.client.HttpClient client = new DefaultHttpClient();

        HttpPut put = new HttpPut(urlPut);
        //  String xml = "<xml>xxxx</xml>";
        HttpEntity entity = new ByteArrayEntity(bodyPut.getBytes("UTF-8"));
        put.addHeader("Content-Type","application/json");
        put.setEntity(entity);
        HttpResponse response = client.execute(put);
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;
    }

    public String post(String urlPost,String body) throws Exception{

        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlPost);
        //  String xml = "<xml>xxxx</xml>";
        HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
        post.addHeader("Content-Type","application/json");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;
    }

    public String get(String url) throws Exception{
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        //  String xml = "<xml>xxxx</xml>";
        // HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
        get.addHeader("Content-Type","application/json");
        //get.setEntity(entity);
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;
    }

    public String delete(String urlDelete,String bodyDelete) throws Exception{

        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpPost delete = new HttpPost(urlDelete);
        //  String xml = "<xml>xxxx</xml>";
        HttpEntity entity = new ByteArrayEntity(bodyDelete.getBytes("UTF-8"));
        delete.addHeader("Content-Type","application/json");
        delete.setEntity(entity);
        HttpResponse response = client.execute(delete);
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;
    }
}
