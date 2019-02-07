package res;


import dao.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utl.DateUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("msg")
public class VisitEventServlet {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("project.jpa.hibernate.mysql");

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting hello(@PathParam("param") String name) {
        return new Greeting(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consumeJson(String jsonString) {
        VisitDao visitDao = new VisitDaoImpl(entityManagerFactory);
        Visit visit=parseVisitJson(jsonString);
        visitDao.persist(visit);

        Date current = DateUtil.atStartOfDay(new Date());
        long numberOfVisits = visitDao.getNumberOfVisits(current, DateUtil.addDays(current,1));
        long numberOfUsers =visitDao.getNumberOfUsers(current, DateUtil.addDays(current,1));

        JSONObject jsnobj = new JSONObject();
        jsnobj.put("cntvst",numberOfVisits);
        jsnobj.put("cntusr", numberOfUsers);

        return String.valueOf(jsnobj);
    }

    private Visit parseVisitJson (String json){
        URLDao urlDao = new URLDaoImpl(entityManagerFactory) ;
        VisitorDao visitorDao = new VisitorDaoImpl(entityManagerFactory);

        JSONParser parser = new JSONParser();
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Visit visit=null;

        try {
            Object obj = parser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;

            URL url = urlDao.getByURL((String) jsonObj.get("url"));
            if (url==null) url = new URL((String) jsonObj.get("url"));

            Visitor visitor = visitorDao.getByUserIdentStr((String) jsonObj.get("visitor"));
            if (visitor==null) visitor = new Visitor((String) jsonObj.get("visitor"));

            visit = new Visit(visitor,url,simpleDateFormat.parse((String)jsonObj.get("visitTimeStamp")));
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return visit;
    }

}
