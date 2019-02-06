package res;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("msg")
public class MyMessage {

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting hello(@PathParam("param") String name) {
        return new Greeting(name);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String helloUsingJson(String jsonString) {

        JSONParser parser = new JSONParser();
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Visit visit=null;

        try {
            Object obj = parser.parse(jsonString);
            JSONObject jsonObj = (JSONObject) obj;
            Date date =  simpleDateFormat.parse((String)jsonObj.get("visitTimeStamp"));
            visit = new Visit(new Visitor("fsdf4234sa")
                          ,new URL((String) jsonObj.get("url"))
                          ,date );
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }



        return jsonString;
    }

}
