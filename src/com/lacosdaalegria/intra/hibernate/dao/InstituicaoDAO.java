package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Instituicao;
import com.lacosdaalegria.intra.hibernate.model.Polo;

public class InstituicaoDAO {
	
	 public List<Instituicao> listaInstituicaos() {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Instituicao> query = session.createQuery("from Instituicao", Instituicao.class);
	            
	            List<Instituicao> queryList = query.getResultList();
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
	 
	 public List<Instituicao> listaInstituicaosRegional(Polo polo) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Instituicao> query = session.createQuery("from Instituicao s where s.ra.polo.id = :id", Instituicao.class);
	            query.setParameter("id", polo.getId());
	            
	            List<Instituicao> queryList = query.getResultList();
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
	 
	    public Instituicao InstituicaoPelaId(Instituicao instituicao) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Instituicao> query = session.createQuery("from Instituicao s where s.id = :id", Instituicao.class);
	            query.setParameter("id", instituicao.getId());
	 
	            List<Instituicao> queryList = query.getResultList();
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
	    
	    public Instituicao InstituicaoPelaId(int int_id) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Instituicao> query = session.createQuery("from Instituicao s where s.id = :id", Instituicao.class);
	            query.setParameter("id", int_id);
	 
	            List<Instituicao> queryList = query.getResultList();
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
	 
	    public void updateInstituicao(Instituicao instituicao) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            Transaction transaction = session.beginTransaction();
	            session.saveOrUpdate(instituicao);
	            transaction.commit();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	 
	    public Instituicao addInstituicao(Instituicao instituicao) {
	        Session session = null;
	        Transaction transaction = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            transaction = session.beginTransaction();
	            session.save(instituicao);
	            transaction.commit();
	            return instituicao;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	        	session.close();
	        }
	    }

}
