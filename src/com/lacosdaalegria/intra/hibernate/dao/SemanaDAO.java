package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Semana;

public class SemanaDAO {
	
	public List<Semana> listaSemana() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Semana> query = session.createQuery("from Semana", Semana.class);
 
            List<Semana> queryList = query.getResultList();
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
	
	public Semana ultimaSemana() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Semana> query = session.createQuery("from Semana where status = 1", Semana.class);
            
            query.setMaxResults(1);
            
            List<Semana> queryList = query.getResultList();

            return queryList.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	
    public Semana SemanaPelaId(Semana Semana) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Semana> query = session.createQuery("from Semana s where s.id = :id", Semana.class);
            query.setParameter("id", Semana.getId());
 
            List<Semana> queryList = query.getResultList();
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
    
    public Semana SemanaPelaId(int SemanaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Semana> query = session.createQuery("from Semana s where s.id = :id", Semana.class);
            query.setParameter("id", SemanaId);
 
            List<Semana> queryList = query.getResultList();
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
    
    public boolean podeCriar(Semana semana) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Semana> query = session.createQuery("from Semana s where s.semana = :semana and s.ano = :ano", Semana.class);
            query.setParameter("semana", semana.getSemana());
            query.setParameter("ano", semana.getAno());
 
            List<Semana> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
      
    public Semana addSemana(Semana semana) {
        Session session = null;
        Transaction transaction = null;
        if(podeCriar(semana)){
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            transaction = session.beginTransaction();
	            session.save(semana);
	            transaction.commit();
	            return semana;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
        } else 
        	 return null;
    }
    
    public void updateSemana(Semana Semana) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Semana);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
