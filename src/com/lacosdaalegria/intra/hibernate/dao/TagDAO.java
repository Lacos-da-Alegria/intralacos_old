package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Tag;

public class TagDAO {
	
	 public List<Tag> listaTags() {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Tag> query = session.createQuery("from Tag", Tag.class);
	 
	            List<Tag> queryList = query.getResultList();
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
	 
	 public List<Tag> listaTagsAtivas() {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Tag> query = session.createQuery("from Tag where status = 1", Tag.class);
	 
	            List<Tag> queryList = query.getResultList();
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
	 
	    public Tag TagPelaId(Tag tag) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Tag> query = session.createQuery("from Tag s where s.id = :id", Tag.class);
	            query.setParameter("id", tag.getId());
	 
	            List<Tag> queryList = query.getResultList();
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
	    
	    public Tag TagPelaId(int tag_id) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            TypedQuery<Tag> query = session.createQuery("from Tag s where s.id = :id", Tag.class);
	            query.setParameter("id", tag_id);
	 
	            List<Tag> queryList = query.getResultList();
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
	 
	    public void updateTag(Tag tag) {
	        Session session = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            Transaction transaction = session.beginTransaction();
	            session.saveOrUpdate(tag);
	            transaction.commit();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	 
	    public Tag addTag(Tag tag) {
	        Session session = null;
	        Transaction transaction = null;
	        try {
	            session = HibernateConnector.getInstance().getSession();
	            transaction = session.beginTransaction();
	            session.save(tag);
	            transaction.commit();
	            return tag;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	        	session.close();
	        }
	    }

}
