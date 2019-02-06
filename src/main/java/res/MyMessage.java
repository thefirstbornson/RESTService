package res;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("msg")
public class MyMessage {

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getMessage() {
//        return "My message\n";
//    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting hello(@PathParam("param") String name) {
        return new Greeting(name);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String helloUsingJson(Greeting greeting) {
        return greeting.getMessage() + "\n";
    }

}
