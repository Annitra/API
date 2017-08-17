import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
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
public class TestCase extends HttpClientRequests {




    @Test (description = "Check the list by GET")
    public void ListOfAllUsers() throws Exception{
        String input= get("http://soft.it-hillel.com.ua:3000/api/users");

        Assert.assertTrue(PatternMatches("^.\\{(?:\"id\":\".+?\",\"name\":\".+?\"|\"name\":\".+?\"),\"phone\":\".+?\",\"role\":\".+?\",.+?}.",input),"There is no Content-Type:application/json ");
    }


    //dependsOnMethods = {"SavingNewUser"}
    @Test (description = "ReSaving new user by PUT")
    public void ReSavingNewUser()throws Exception{

       //Not work yet
        /*
       String postResponse=post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
       System.out.println(postResponse);
       System.out.println(getPostId());
       //String urlPut ="http://soft.it-hillel.com.ua:3000/api/users"+"/"+getPostId();
       String input=put("http://soft.it-hillel.com.ua:3000/api/users"+"/"+getPostId(),
               "{\"id\":"+getPostId()+PatternMethod("\"n.+,",postResponse)+",\"strickes\":\"1\",}");
        */

        String input=put("http://soft.it-hillel.com.ua:3000/api/users/136","{\"id\": \"136\", name: \"User1\", phone: \"123456\", role: \"Student\", \"strikes\": \"1\", \"location\": \"\"}");
        System.out.println(input);
        Assert.assertTrue(PatternMatches("^\\{\"id\": [0-9]+.+}$",input),"No lists");
    }



    @Test (description = "New user, method POST, return id ")
    public void SavingNewUser() throws Exception {
      String input =post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
      Assert.assertTrue(PatternMatches("^(?:.+)\"id\":(?:[0-9]+).$",input),"New user doesn't get id");

    }

    @Test (description = "Delete user, method Delete, return id")
    public void DeleteUSer()throws Exception{
        String input=delete("http://soft.it-hillel.com.ua:3000/api/users/136",
                "{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
        System.out.println(input);
        Assert.assertTrue(PatternMatches("^(?:.+)\"id\":(?:[0-9]+).$",input),"Not delete");
    }
}
