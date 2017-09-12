package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Diretoria;

public class DiretoriaDAO {
	
	public List<Diretoria> listaDiretoria() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Diretoria> query = session.createQuery("from Diretoria", Diretoria.class);
 
            List<Diretoria> queryList = query.getResultList();
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
	
    public Diretoria DiretoriaPelaId(Diretoria Diretoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Diretoria> query = session.createQuery("from Diretoria s where s.id = :id", Diretoria.class);
            query.setParameter("id", Diretoria.getId());
 
            List<Diretoria> queryList = query.getResultList();
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
    
    public Diretoria DiretoriaPelaId(int DiretoriaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Diretoria> query = session.createQuery("from Diretoria s where s.id = :id", Diretoria.class);
            query.setParameter("id", DiretoriaId);
 
            List<Diretoria> queryList = query.getResultList();
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
    
    public Diretoria DiretoriaPeloTipo(int tipo) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Diretoria> query = session.createQuery("from Diretoria s where s.tipo = :tipo", Diretoria.class);
            query.setParameter("tipo", tipo);
 
            List<Diretoria> queryList = query.getResultList();
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
    
    public void updateDiretoria(Diretoria Diretoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Diretoria);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Diretoria addDiretoria(Diretoria Diretoria) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Diretoria);
            transaction.commit();
            return Diretoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
