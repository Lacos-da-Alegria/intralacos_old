package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Notificacao;

public class NotificacaoDAO {
	
	public List<Notificacao> listaNotificacao() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Notificacao> query = session.createQuery("from Notificacao", Notificacao.class);
 
            List<Notificacao> queryList = query.getResultList();
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
	
	public List<Notificacao> ultimasCinco() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Notificacao> query = session.createQuery("from Notificacao order by id desc", Notificacao.class).setMaxResults(5);
 
            List<Notificacao> queryList = query.getResultList();
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
	
    public Notificacao NotificacaoPelaId(Notificacao Notificacao) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Notificacao> query = session.createQuery("from Notificacao s where s.id = :id", Notificacao.class);
            query.setParameter("id", Notificacao.getId());
 
            List<Notificacao> queryList = query.getResultList();
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
    
    public Notificacao NotificacaoPelaId(int NotificacaoId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Notificacao> query = session.createQuery("from Notificacao s where s.id = :id", Notificacao.class);
            query.setParameter("id", NotificacaoId);
 
            List<Notificacao> queryList = query.getResultList();
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
    
    public void updateNotificacao(Notificacao Notificacao) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Notificacao);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Notificacao addNotificacao(Notificacao Notificacao) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Notificacao);
            transaction.commit();
            return Notificacao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
