package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atividade;

public class AtividadeDAO {
	
	 public List<Atividade> listaAtividades() {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Atividade> query = session.createQuery("from Atividade", Atividade.class);
	            
	            List<Atividade> queryList = query.getResultList();
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
	 
	 public List<Atividade> listaAtividadesAtivas() {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Atividade> query = session.createQuery("from Atividade where status = 1", Atividade.class);
	            
	            List<Atividade> queryList = query.getResultList();
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
	 
	    public Atividade AtividadePelaId(Atividade Atividade) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Atividade> query = session.createQuery("from Atividade s where s.id = :id", Atividade.class);
	            query.setParameter("id", Atividade.getId());
	 
	            List<Atividade> queryList = query.getResultList();
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
	    
	    public Atividade AtividadePelaId(int ativ_id) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Atividade> query = session.createQuery("from Atividade s where s.id = :id", Atividade.class);
	            query.setParameter("id", ativ_id);
	 
	            List<Atividade> queryList = query.getResultList();
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
	 
	    public void updateAtividade(Atividade Atividade) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            Transaction transaction = session.beginTransaction();
	            session.saveOrUpdate(Atividade);
	            transaction.commit();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	 
	    public Atividade addAtividade(Atividade Atividade) {
	        Session session = null;
	        Transaction transaction = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            transaction = session.beginTransaction();
	            session.save(Atividade);
	            transaction.commit();
	            return Atividade;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	        	session.close();
	        }
	    }

}
