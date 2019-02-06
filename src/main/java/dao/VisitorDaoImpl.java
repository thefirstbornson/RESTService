package dao;

import res.URL;
import res.Visitor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class VisitorDaoImpl extends AbstractDao implements VisitorDao{
    public VisitorDaoImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }


    @Override
    public Visitor getByUserIdentStr(String userIdentStr) {
        EntityManager em = super.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = super.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Visitor> criteria = builder.createQuery(Visitor.class );
        Root<Visitor> visitorRoot = criteria.from( Visitor.class );
        criteria.select( visitorRoot );
        criteria.where( builder.equal( visitorRoot.get("userIdentStr"), userIdentStr) );
        Visitor visitorObj = null;
        try {
            visitorObj = (Visitor) em.createQuery(criteria).getSingleResult();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }

        return visitorObj;
    }
}
