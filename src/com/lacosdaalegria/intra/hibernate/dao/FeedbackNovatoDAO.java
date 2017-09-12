package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.FeedbackNovato;

public class FeedbackNovatoDAO {
	
	public List<FeedbackNovato> listaFeedbackNovato() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<FeedbackNovato> query = session.createQuery("from FeedbackNovato", FeedbackNovato.class);
 
            List<FeedbackNovato> queryList = query.getResultList();
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
	
    public FeedbackNovato FeedbackNovatoPelaId(FeedbackNovato FeedbackNovato) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<FeedbackNovato> query = session.createQuery("from FeedbackNovato s where s.id = :id", FeedbackNovato.class);
            query.setParameter("id", FeedbackNovato.getId());
 
            List<FeedbackNovato> queryList = query.getResultList();
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
    
    public FeedbackNovato FeedbackNovatoPelaId(int FeedbackNovatoId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<FeedbackNovato> query = session.createQuery("from FeedbackNovato s where s.id = :id", FeedbackNovato.class);
            query.setParameter("id", FeedbackNovatoId);
 
            List<FeedbackNovato> queryList = query.getResultList();
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
    
    public void updateFeedbackNovato(FeedbackNovato FeedbackNovato) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(FeedbackNovato);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public FeedbackNovato addFeedbackNovato(FeedbackNovato FeedbackNovato) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(FeedbackNovato);
            transaction.commit();
            return FeedbackNovato;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
