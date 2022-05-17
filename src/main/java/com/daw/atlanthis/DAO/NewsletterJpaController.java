package com.daw.atlanthis.DAO;

import com.daw.atlanthis.DAO.exceptions.NonexistentEntityException;
import com.daw.atlanthis.DTO.Newsletter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class NewsletterJpaController implements Serializable {

    public NewsletterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Newsletter newsletter) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(newsletter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Newsletter newsletter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            newsletter = em.merge(newsletter);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = newsletter.getId();
                if (findNewsletter(id) == null) {
                    throw new NonexistentEntityException("The newsletter with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Newsletter newsletter;
            try {
                newsletter = em.getReference(Newsletter.class, id);
                newsletter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The newsletter with id " + id + " no longer exists.", enfe);
            }
            em.remove(newsletter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Newsletter> findNewsletterEntities() {
        return findNewsletterEntities(true, -1, -1);
    }

    public List<Newsletter> findNewsletterEntities(int maxResults, int firstResult) {
        return findNewsletterEntities(false, maxResults, firstResult);
    }

    private List<Newsletter> findNewsletterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Newsletter.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Newsletter findNewsletter(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Newsletter.class, id);
        } finally {
            em.close();
        }
    }

    public int getNewsletterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Newsletter> rt = cq.from(Newsletter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
