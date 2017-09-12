package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.RegiaoAdministrativa;

public class RegiaoAdministrativaDAO {
	
	public List<RegiaoAdministrativa> listaRegiaoAdministrativa() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegiaoAdministrativa> query = session.createQuery("from RegiaoAdministrativa", RegiaoAdministrativa.class);
 
            List<RegiaoAdministrativa> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	
	public List<RegiaoAdministrativa> listaRegiaoAdminPolo(int poloId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegiaoAdministrativa> query = session.createQuery("from RegiaoAdministrativa where polo_id = :id", RegiaoAdministrativa.class);
            query.setParameter("id", poloId);
            
            List<RegiaoAdministrativa> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	
	public List<RegiaoAdministrativa> listaRADesviculadas() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegiaoAdministrativa> query = session.createQuery("from RegiaoAdministrativa where polo_id is null", RegiaoAdministrativa.class);
 
            List<RegiaoAdministrativa> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	
    public RegiaoAdministrativa RegiaoAdministrativaPelaId(RegiaoAdministrativa regiaoAdministrativa) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegiaoAdministrativa> query = session.createQuery("from RegiaoAdministrativa s where s.id = :id", RegiaoAdministrativa.class);
            query.setParameter("id", regiaoAdministrativa.getId());
 
            List<RegiaoAdministrativa> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public RegiaoAdministrativa RegiaoAdministrativaPelaId(int raId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegiaoAdministrativa> query = session.createQuery("from RegiaoAdministrativa s where s.id = :id", RegiaoAdministrativa.class);
            query.setParameter("id", raId);
 
            List<RegiaoAdministrativa> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public void updateRegiaoAdministrativa(RegiaoAdministrativa regiaoAdministrativa) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(regiaoAdministrativa);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public RegiaoAdministrativa addRegiaoAdministrativa(RegiaoAdministrativa regiaoAdministrativa) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(regiaoAdministrativa);
            transaction.commit();
            return regiaoAdministrativa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
