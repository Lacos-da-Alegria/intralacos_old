package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atendimento;

public class AtendimentoDAO {
	
	public List<Atendimento> listaAtendimento() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Atendimento> query = session.createQuery("from Atendimento", Atendimento.class);
 
            
            List<Atendimento> queryList = query.getResultList();
            
            for(Atendimento atend : queryList){
            	Hibernate.initialize(atend.getCategorias());
        		Hibernate.initialize(atend.getAtendentes());
            }
            
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
	
    public Atendimento AtendimentoPelaId(Atendimento Atendimento) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Atendimento> query = session.createQuery("from Atendimento s where s.id = :id", Atendimento.class);
            query.setParameter("id", Atendimento.getId());
 
            List<Atendimento> queryList = query.getResultList();
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
    
    public Atendimento AtendimentoPelaId(int AtendimentoId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Atendimento> query = session.createQuery("from Atendimento s where s.id = :id", Atendimento.class);
            query.setParameter("id", AtendimentoId);
 
            List<Atendimento> queryList = query.getResultList();
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
    
    public void updateAtendimento(Atendimento Atendimento) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Atendimento);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Atendimento addAtendimento(Atendimento Atendimento) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Atendimento);
            transaction.commit();
            return Atendimento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
