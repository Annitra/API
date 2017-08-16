import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.io.InputStream;

public class Network {

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();



    public void getRequest(String reqUrl) throws IOException {
        GenericUrl url = new GenericUrl(reqUrl);

        HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
        HttpResponse response = request.execute();
        System.out.println(response.getStatusCode());

        InputStream is = response.getContent();
        int ch;
        while ((ch = is.read()) != -1) {
            System.out.print((char) ch);
        }
        response.disconnect();
    }
}
