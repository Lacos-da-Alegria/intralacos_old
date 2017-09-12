package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Demanda;
import com.lacosdaalegria.intra.hibernate.model.Nota;

public class NotaDAO {
	
	public List<Nota> listaNota() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Nota> query = session.createQuery("from Nota", Nota.class);
 
            List<Nota> queryList = query.getResultList();
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
	
	public List<Nota> listaNota(Demanda demanda) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Nota> query = session.createQuery("from Nota where demanda.id = :id", Nota.class);
            query.setParameter("id", demanda.getId());
 
            List<Nota> queryList = query.getResultList();
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
	
    public Nota NotaPelaId(Nota Nota) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Nota> query = session.createQuery("from Nota s where s.id = :id", Nota.class);
            query.setParameter("id", Nota.getId());
 
            List<Nota> queryList = query.getResultList();
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
    
    public Nota NotaPelaId(int NotaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Nota> query = session.createQuery("from Nota s where s.id = :id", Nota.class);
            query.setParameter("id", NotaId);
 
            List<Nota> queryList = query.getResultList();
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
    
    public void updateNota(Nota Nota) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Nota);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Nota addNota(Nota Nota) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Nota);
            transaction.commit();
            return Nota;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
