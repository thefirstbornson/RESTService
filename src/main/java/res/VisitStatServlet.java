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

@Path("stat")
public class VisitStatServlet {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("project.jpa.hibernate.mysql");

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consumeJson(String jsonString) {
        VisitDao visitDao = new VisitDaoImpl(entityManagerFactory);
        Date fromDate =null,toDate = null;

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);
            JSONObject inJsonObj = (JSONObject) obj;
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            fromDate=simpleDateFormat.parse((String)inJsonObj.get("from"));
            toDate=simpleDateFormat.parse((String)inJsonObj.get("to"));

        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        long numberOfVisits = visitDao
                              .getNumberOfVisits(DateUtil.atStartOfDay(fromDate), DateUtil.atEndOfDay(toDate));
        long numberOfUsers =visitDao
                              .getNumberOfUsers(DateUtil.atStartOfDay(fromDate), DateUtil.atEndOfDay(toDate));
        long numberOfLoyal = visitDao
                                .getNumberOfLoyalUsers(DateUtil.atStartOfDay(fromDate), DateUtil.atEndOfDay(toDate),10);

        JSONObject jsnobj = new JSONObject();
        jsnobj.put("cntvst",numberOfVisits);
        jsnobj.put("cntusr", numberOfUsers);
        jsnobj.put("cntloyal", numberOfLoyal);

        return String.valueOf(jsnobj);
    }

}

