import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by User on 13.08.2017.
 */
public class TestCase  {
 HttpClientRequests http;

 public String url="http://soft.it-hillel.com.ua:3000/";
 protected String urlPost ="http://soft.it-hillel.com.ua:3000/api/users";
 public String bodyPost = "{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}";
    public String put (String postResponse) throws Exception{
       String urlPut=urlPost+getPostId();
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(urlPut);
        //  String xml = "<xml>xxxx</xml>";
        HttpEntity entity = new ByteArrayEntity(bodyPost.getBytes("UTF-8"));
        put.addHeader("Content-Type","application/json");
        put.setEntity(entity);
        HttpResponse response = client.execute(put);
        String result = EntityUtils.toString(response.getEntity());
        //System.out.println(result);
        return result;
    }

    public String post(String urlPost,String body) throws Exception{

        HttpClient client = new DefaultHttpClient();
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
        HttpClient client = new DefaultHttpClient();
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

    public String getPostId()throws Exception{
        String postResponse=post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
        Pattern userId=Pattern.compile("\"id\":[0-9]+");
        Matcher mId=userId.matcher(postResponse);
        String user_Id=mId.group();

        Pattern id=Pattern.compile("[0-9]+");
        Matcher m_Id=id.matcher(user_Id);
        String idUser=m_Id.group();

        //return postResponse;
        return idUser;

    }
    @Test (description = "Check the list by GET")
    public void ListOfAllUsers() throws Exception{
        String input=get("http://soft.it-hillel.com.ua:3000/api/users");
        Pattern p = Pattern.compile("");
        Matcher m = p.matcher(input);

        Assert.assertTrue(m.matches(),"There is no Content-Type:application/json ");
    }

    //dependsOnMethods = {"SavingNewUser"}
    @Test (description = "ReSaving new user by PUT")
    public void ReSavingNewUser()throws Exception{
       String postResponse=post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
       String input=put(postResponse);

        Pattern p=Pattern.compile("^(?:.+)\"id\":(?:[0-9]+).$");
        Matcher m = p.matcher(input);
        Assert.assertTrue(m.matches(),"No lists");
    }



    @Test (description = "New user, method POST, return id ")
    public void SavingNewUser() throws Exception {
      String input =post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
        Pattern p=Pattern.compile("^(?:.+)\"id\":(?:[0-9]+).$");
        Matcher m=p.matcher(input);
        Assert.assertTrue(m.matches(),"New user doesn't get id");

    }
}
