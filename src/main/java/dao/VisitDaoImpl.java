package dao;

import res.Visit;
import res.Visitor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class VisitDaoImpl extends AbstractDao implements VisitDao{

    public VisitDaoImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }


    public long getNumberOfVisits(Date start, Date finish) {
        EntityManager em = super.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = super.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class );
        Root<Visit> visitRoot = criteria.from( Visit.class );
        criteria.select( builder.count(visitRoot.get("visitor")));
        criteria.where( builder.between(visitRoot.get("visitTimeStamp").as(Date.class), start, finish) );
        Long cnt = em.createQuery( criteria ).getSingleResult();
        em.close();
        return cnt;
    }

    public long getNumberOfUsers (Date start, Date finish) {
        EntityManager em = super.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = super.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class );
        Root<Visit> visitRoot = criteria.from( Visit.class );
        criteria.select( builder.countDistinct(visitRoot.get("visitor")));
        criteria.where( builder.between(visitRoot.get("visitTimeStamp").as(Date.class), start, finish) );
        Long cnt = em.createQuery( criteria ).getSingleResult();
        em.close();
        return cnt;
    }

    public long getNumberOfLoyalUsers (Date start, Date finish, int numberOfPages) {

        EntityManager em = super.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = super.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Visit> criteria = builder.createQuery(Visit.class );

        Root<Visit> visitRoot = criteria.from( Visit.class );
        criteria.select(visitRoot.get("visitor"));
        criteria.where( builder.between(visitRoot.get("visitTimeStamp").as(Date.class), start, finish) );
        criteria.groupBy(visitRoot.get("url"));
        criteria.having(builder.ge(builder.count(visitRoot.get("visitor")), numberOfPages));

        Long cnt = (long) em.createQuery( criteria ).getResultList().size();
        em.close();
        return cnt;
    }

}
