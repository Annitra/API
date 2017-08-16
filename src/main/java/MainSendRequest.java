import java.io.FileOutputStream;
import java.io.IOException;
/*
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MainSendRequest {

    static String url =
            "http://localhost:8080/HttpRequestSample/RequestSend.jsp";

    public static void main(String[] args) {

        //Instantiate an HttpClient
        HttpClient client = new HttpClient();

        //Instantiate a GET HTTP method
        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Content-type",
                "text/xml; charset=ISO-8859-1");

        //Define name-value pairs to set into the QueryString
        NameValuePair nvp1= new NameValuePair("firstName","fname");
        NameValuePair nvp2= new NameValuePair("lastName","lname");
        NameValuePair nvp3= new NameValuePair("email","email@email.com");

        method.setQueryString(new NameValuePair[]{nvp1,nvp2,nvp3});

        try{
            int statusCode = client.executeMethod(method);

            System.out.println("Status Code = "+statusCode);
            System.out.println("QueryString>>> "+method.getQueryString());
            System.out.println("Status Text>>>"
                    +HttpStatus.getStatusText(statusCode));

            //Get data as a String
            System.out.println(method.getResponseBodyAsString());

            //OR as a byte array
            byte [] res  = method.getResponseBody();

            //write to file
            FileOutputStream fos= new FileOutputStream("donepage.html");
            fos.write(res);

            //release connection
            method.releaseConnection();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
*/