package dao;

import dao.*;
import org.junit.Before;
import org.junit.Test;
import res.URL;
import res.Visit;
import res.Visitor;
import utl.DateUtil;

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
        entityManagerFactory = Persistence.createEntityManagerFactory("project.jpa.hibernate.h2");
        visitDAO = new VisitDaoImpl(entityManagerFactory);
        urlDao = new URLDaoImpl(entityManagerFactory);
        visitorDao = new VisitorDaoImpl(entityManagerFactory);
    }

    @Before
    public void initialize() {
        visit = new VisitDAOImplTest().createVisit();
        visitDAO.persist(visit);
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
        assertEquals("Неверное количество", 1,  i);
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
        assertEquals("Неверное количество", 1,  i);
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
        String str = "https://valve.github.io/fin";;
        for (int x = 0; x < 10; x++){
            visitDAO.persist(createVisit(str));
            str =str+x;
        }

        long i = visitDAO.getNumberOfLoyalUsers(start, finish,1);
        assertEquals("Неверное количество", 1,  i);
        System.out.println(i);
    }

    @Test
    public void persistVisitTest() {

        //Visit persistedVisit = (Visit) visitDAO.persist(visit);
        Visit returnedVisit = (Visit) visitDAO.getByPK(Visit.class,1);
        assertEquals("Возвращено неверное значение", 1,  returnedVisit.getId());
    }

    private Visit createVisit()  {
        return createVisit("https://valve.github.io/finis");
    }

    private Visit createVisit(String urlStr){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse("06-02-2019 12:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        URL url = urlDao.getByURL(urlStr);
        if (url==null) url = new URL(urlStr);

        Visitor visitor = visitorDao.getByUserIdentStr("123454b8aab33163ce0498ff7fc89587");
        if (visitor==null) visitor = new Visitor("123454b8aab33163ce0498ff7fc89587");

        return new Visit(visitor,url,date);
    }

}