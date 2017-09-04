import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Requests.RequestsTools;



/**
 * Created by User on 13.08.2017.
 */
public class TestCase extends HttpClientRequests {
    String userId="5";

    @DataProvider(name = "TestData")
    public Object[][] createData(){
        return new Object[][] {
                {""},
                {"Student"},
                {"Support"},
                {"Admin"},
                {"Role"},
                        };
    }

    public void findUserID (String data) {
        Pattern findID = Pattern.compile("\"id\":\"([0-9]+)");
        Matcher m = findID.matcher(data);
        if (m.find()) {
            userId = m.group(1);
        }
    }

    public void createUser(String role) {
        String data ="\"name\":\"API_Testing\",\"role\":\"";
        String[] responseData = RequestsTools.postRequest("http://soft.it-hillel.com.ua:3000/api/users/"+data+role+"\"");
        return responseData;
    }

    public void saveUser(String role){
        String data = "\"name\":\"API_Testing\",\"role\":\"";
        String[] responseData= RequestsTools.putRequest("http://soft.it-hillel.com.ua:3000/api/users/"+userId, '{' + data +role+"\""+'}');
        return responseData;
    }

    @Test (description = "Getting users' list")
    public void getListUsers()throws IOException{
        String[] responseData = RequestsTools.getRequest("http://soft.it-hillel.com.ua:3000/api/users");
        Assert.assertTrue(responseData[1].contains("[{\"id\":\""));
        findUserID(responseData[1]);
        System.out.println(userId);
        RequestsTools.checkContentType(responseData[0]);

    }


    @Test(dataProvider = "TestData", description = "Saving user with choosen id")
    public void savingUser(String role)throws IOException{
        String[] responseData;
        switch (role){
            case "" :  responseData=saveUser(role);; break;
            case "Student" :  responseData=saveUser(role);; break;
            case "Support" : Assert.assertTrue(role=="Support");responseData=saveUser(role);;break;
            case "Admin" : Assert.assertTrue(role=="Admin"); responseData=saveUser(role); ;break;
            default:  return "401";
        }
        Assert.assertTrue(RequestsTools.getUserInfo(userId).contains("\"name\":\"Test2\",\"phone\":\"Test2\",\"role\":\""));
        RequestsTools.checkContentType(responseData[0]);
    }


    @Test(dataProvider = "TestData", description = "Creating new user: Student or without,Support, Admin")
    public void createNewUser(String role)throws Exception{
        String[] responseData;
        switch (role){
            case "" :  responseData=createUser(role);; break;
            case "Student" :  responseData=createUser(role);; break;
            case "Support" : Assert.assertTrue(role=="Support");responseData=createUser(role);;break;
            case "Admin" : Assert.assertTrue(role=="Admin"); responseData=createUser(role); ;break;
            default:  return "401";
        }
        Assert.assertTrue(RequestsTools.finNewID(responseData[1]));
        RequestsTools.checkContentType(responseData[0]);
    }

    @Test(description = "Delete user with userID")
    public void deleteUser()throws Exception{
        // RequestsTools.deleteRequest("http://soft.it-hillel.com.ua:3000/api/users/"+userId);
        System.out.println(RequestsTools.deleteRequest("http://soft.it-hillel.com.ua:3000/api/users/"+userId));
    }




}
