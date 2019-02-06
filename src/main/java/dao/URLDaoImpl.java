package dao;

import res.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class URLDaoImpl extends AbstractDao implements URLDao {
    public URLDaoImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public URL getByURL(String url) {
        EntityManager em = super.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = super.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<URL> criteria = builder.createQuery( URL.class );
        Root<URL> urlRoot = criteria.from( URL.class );
        criteria.select( urlRoot );
        criteria.where( builder.equal( urlRoot.get("pageURL"), url) );
        URL urlObj = null;
        try {
            urlObj = (URL) em.createQuery(criteria).getSingleResult();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }

        return urlObj;
    }
}
