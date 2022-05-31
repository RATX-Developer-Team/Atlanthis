package com.daw.atlanthis.DAO;

import com.daw.atlanthis.DAO.exceptions.NonexistentEntityException;
import com.daw.atlanthis.DTO.Hilos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class HilosJpaController implements Serializable {

    public HilosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hilos hilos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hilos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hilos hilos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hilos = em.merge(hilos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hilos.getCodHilo();
                if (findHilos(id) == null) {
                    throw new NonexistentEntityException("The hilos with id " + id + " no longer exists.");
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
            Hilos hilos;
            try {
                hilos = em.getReference(Hilos.class, id);
                hilos.getCodHilo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hilos with id " + id + " no longer exists.", enfe);
            }
            em.remove(hilos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hilos> findHilosEntities() {
        return findHilosEntities(true, -1, -1);
    }

    public List<Hilos> findHilosEntities(int maxResults, int firstResult) {
        return findHilosEntities(false, maxResults, firstResult);
    }

    private List<Hilos> findHilosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hilos.class));
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

    public Hilos findHilos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hilos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHilosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hilos> rt = cq.from(Hilos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int findBySubcategoria(int c){
        EntityManager em = getEntityManager();
        TypedQuery query=em.createNamedQuery("Hilos.findByCodSubcategoria", Hilos.class);
        query.setParameter("codSubcategoria", c);
        List<Hilos> x = query.getResultList();
        return x.size();
    }
    
    public Hilos lastHilo(int c){
        EntityManager em = getEntityManager();
        TypedQuery query=em.createNamedQuery("Hilos.findByCodSubcategoria", Hilos.class);
        query.setParameter("codSubcategoria", c);
        List<Hilos> x = query.getResultList();
        Hilos hilo_ = null;
        for(Hilos o:x) {
            hilo_ = o;
        }
        return hilo_;
    }
}
