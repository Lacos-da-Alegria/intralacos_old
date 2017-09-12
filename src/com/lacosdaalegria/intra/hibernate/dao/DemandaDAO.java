package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Demanda;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class DemandaDAO {
	
	public List<Demanda> listaDemanda() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Demanda> query = session.createQuery("from Demanda", Demanda.class);
 
            List<Demanda> queryList = query.getResultList();
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
	
	public List<Demanda> listaDemandaAtivas() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Demanda> query = session.createQuery("from Demanda where status != 4", Demanda.class);
 
            List<Demanda> queryList = query.getResultList();
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
	
	public List<Demanda> demandasVoluntario(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Demanda> query = session.createQuery("from Demanda where status != 4 and responsavel = :vol", Demanda.class);
            query.setParameter("vol", voluntario);
            
            List<Demanda> queryList = query.getResultList();
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
	
    public void DemandaPelaId(Demanda Demanda) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Demanda> query = session.createQuery("from Demanda s where s.id = :id", Demanda.class);
            query.setParameter("id", Demanda.getId());
 
            List<Demanda> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
            	Demanda =  null;
            } else {
            	Demanda = queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Demanda = null;
        } finally {
            session.close();
        }
    }
    
    public Demanda DemandaPelaId(int DemandaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Demanda> query = session.createQuery("from Demanda s where s.id = :id", Demanda.class);
            query.setParameter("id", DemandaId);
 
            List<Demanda> queryList = query.getResultList();
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
    
    public void updateDemanda(Demanda Demanda) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Demanda);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Demanda addDemanda(Demanda Demanda) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Demanda);
            transaction.commit();
            return Demanda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
