import Requests.Requests;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hillel on 18.08.17.
 */
public class Tests {
    String userId;
    String id;

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

    @DataProvider(name = "CreateUser")
    public Object[][] createData2(){
        return new Object[][] {
                {"\"name\":\"API_Testing\",\"phone\":\"123456\""},
                {"\"name\":\"API_Testing\",\"phone\":\"123456\",\"role\":\"Student\""},
                {"\"name\":\"API_Testing\",\"phone\":\"123456\",\"role\":\"Support\""},
                {"\"name\":\"API_Testing\",\"phone\":\"123456\",\"role\":\"Admin\""},

        };
    }
    private void findUserID (String data) {
        Pattern findID = Pattern.compile("[{\"role\":\"Student\",\"id\":\"(\\d+)");
        Matcher m = findID.matcher(data);
        if (m.find()) {
            userId = m.group(1);
        }
    }

    private String  userId (String data) {

        Pattern findID = Pattern.compile("\"id\":\"(\\d+)");
        Matcher m = findID.matcher(data);
        if (m.find()){ id = m.group(1);}
           return id;     }

    private void checkContentType(String headers) {
        Assert.assertTrue(headers.contains("Content-Type: application/json"));
    }

    @Test(description = "Second requirement - getting user list")
    void getUsers() throws IOException {
        String[] responseData = Requests.sendGet("http://soft.it-hillel.com.ua:3000/api/users");
        System.out.println(responseData[0]);
        System.out.println(responseData[1]);

        //"role":"Student","id":"
        Assert.assertTrue(responseData[1].contains("[{\"role\":\"Student\",\"id\":"));

        findUserID(responseData[1]);
        //checkContentType(responseData[0]);
    }

    @Test(description = "Third requirement - saving users")
    void saveUser() throws IOException {
       String  data = "\"name\":\"API_Testing\"";
        System.out.println(userId);
        //String[] responseData = Requests.sendPut("http://soft.it-hillel.com.ua:3000/api/users/"+ userId, '{' + data + '}');

        //System.out.println(Requests.getUserInfo(userId));
        //Assert.assertTrue(Requests.getUserInfo(userId).contains(data));

       // checkContentType(responseData[0]);
    }

    @Test(dataProvider = "CreateUser",description = "")
    void createUser(String data) throws IOException{
        //String data = "\"name\":\"API_Testing\",\"phone\":\"123456\",\"role\":\"";
        String[] responseData = Requests.sendPost("http://soft.it-hillel.com.ua:3000/api/users",data);
        String id = userId(responseData[1]);
        System.out.println(responseData);
        System.out.println(id);
        //String list = Requests.sendGet("http://soft.it-hillel.com.ua:3000/api/users")[0];
       // Assert.assertTrue(Requests.getUserInfo(id).contains(data));
        checkContentType(responseData[0]);
    }

    @Test(description = "Delete")
    void deleteUser()throws IOException{
        String[] responseData = Requests.sendDelete(userId);
        Assert.assertTrue(responseData[1].contains("[{\"id\":\""));
        Assert.assertTrue(Requests.getUserInfo(userId)=="No data found.");

        findUserID(responseData[1]);
        checkContentType(responseData[0]);
    }
}
