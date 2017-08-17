import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 17.08.2017.
 */
public class Methods {
    public boolean PatternMatches(String pat,String input){
        Pattern p=Pattern.compile(pat);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    //String pat="\"n.+[id]";
    public String PatternMethod(String pat,String input){
        Pattern p=Pattern.compile(pat);
        Matcher m = p.matcher(input);
        //m.find(input);
        return m.group();
    }

// Not work yet
    /*
    public String getPostId()throws Exception{
        String postResponse=post("http://soft.it-hillel.com.ua:3000/api/users","{\"name\": \"User1\", \"phone\": \"123456\", \"role\": \"Student\"}");
        Pattern userId=Pattern.compile("\"id\":[0-9]+");
        Matcher mId=userId.matcher(postResponse);
        String user_Id=mId.group(0);



        Pattern id=Pattern.compile("[0-9]+");
        Matcher m_Id=id.matcher(user_Id);
        // m_Id.find(user_Id);
        // String idUser=m_Id.group(0);
        //return m.find() ? m.group() : null;

        //return postResponse;
        return m_Id.find() ? m_Id.group() : null;
        //System.out.println(user_Id);
        //return  user_Id.toString();
    }
    */
}
