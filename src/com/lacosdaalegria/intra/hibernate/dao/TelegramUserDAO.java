package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Demanda;
import com.lacosdaalegria.intra.hibernate.model.TelegramUser;

public class TelegramUserDAO {
	
	public List<TelegramUser> listaTelegramUser() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<TelegramUser> query = session.createQuery("from TelegramUser", TelegramUser.class);
 
            List<TelegramUser> queryList = query.getResultList();
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
	
	public List<TelegramUser> listaTelegramUser(Demanda demanda) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<TelegramUser> query = session.createQuery("from TelegramUser where demanda.id = :id", TelegramUser.class);
            query.setParameter("id", demanda.getId());
 
            List<TelegramUser> queryList = query.getResultList();
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
	
    public TelegramUser TelegramUserPelaId(TelegramUser TelegramUser) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<TelegramUser> query = session.createQuery("from TelegramUser s where s.id = :id", TelegramUser.class);
            query.setParameter("id", TelegramUser.getId());
 
            List<TelegramUser> queryList = query.getResultList();
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
    
    public TelegramUser TelegramUserPelaId(int TelegramUserId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<TelegramUser> query = session.createQuery("from TelegramUser s where s.id = :id", TelegramUser.class);
            query.setParameter("id", TelegramUserId);
 
            List<TelegramUser> queryList = query.getResultList();
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
    
    public void updateTelegramUser(TelegramUser TelegramUser) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(TelegramUser);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public TelegramUser addTelegramUser(TelegramUser TelegramUser) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(TelegramUser);
            transaction.commit();
            return TelegramUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
