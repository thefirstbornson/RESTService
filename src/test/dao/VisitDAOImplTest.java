package dao;

import org.junit.Before;
import org.junit.Test;
import res.URL;
import res.Visit;
import res.Visitor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class VisitDAOImplTest {

    private final EntityManagerFactory entityManagerFactory;
    VisitDao visitDAO;
    Visit visit;
    URLDao urlDao;
    Visitor visitor;
    VisitorDao visitorDao;

    public VisitDAOImplTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("project.jpa.hibernate.mysql");
        visitDAO = new VisitDaoImpl(entityManagerFactory);
        urlDao = new URLDaoImpl(entityManagerFactory);
        visitorDao = new VisitorDaoImpl(entityManagerFactory);
    }

    @Before
    public void initialize() {
        visit = new VisitDAOImplTest().createVisit();
        System.out.println(visit.getId());
    }

    @Test
    public void getNumberOfVisitsTest (){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date start = null;
        Date finish =null;
        try {
            start =  simpleDateFormat.parse("06-02-2019 00:00:00");
            finish =  simpleDateFormat.parse("07-02-2019 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long i = visitDAO.getNumberOfVisits(start, finish);
        assertEquals("Неверное количество", 8,  i);
    }

    @Test
    public void getNumberOfUsersTest (){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date start = null;
        Date finish =null;
        try {
            start =  simpleDateFormat.parse("6-2-2019 00:00:00");
            finish =  simpleDateFormat.parse("7-2-2019 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long i = visitDAO.getNumberOfUsers(start, finish);
        assertEquals("Неверное количество", 4,  i);
        System.out.println(i);
    }

    @Test
    public void getNumberOfLoyalUsersTest (){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date start = null;
        Date finish =null;
        try {
            start =  simpleDateFormat.parse("06-02-2019 00:00:00");
            finish =  simpleDateFormat.parse("07-02-2019 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long i = visitDAO.getNumberOfLoyalUsers(start, finish,2);
        assertEquals("Неверное количество", 2,  i);
        System.out.println(i);
    }
    @Test
    public void persistVisitTest() {

        Visit persistedVisit = (Visit) visitDAO.persist(visit);
        Visit returnedVisit = (Visit) visitDAO.getByPK(Visit.class,persistedVisit.getId());
        assertEquals("Неверное количество", persistedVisit.getId(),  returnedVisit.getId());
    }

    private Visit createVisit()  {
        Date date = new Date();

        URL url = urlDao.getByURL("https://valve.github.io/finish");
        if (url==null) url = new URL("https://valve.github.io/finish");

        Visitor visitor = visitorDao.getByUserIdentStr("123454b8aab33163ce0498ff7fc89587");
        if (visitor==null) visitor = new Visitor("123454b8aab33163ce0498ff7fc89587");

        return new Visit(visitor,url,date);
    }
}