package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Equipe;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class EquipeDAO {
	
	public List<Equipe> listaEquipe() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Equipe> query = session.createQuery("from Equipe", Equipe.class);
 
            List<Equipe> queryList = query.getResultList();
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
	
    public Equipe EquipePelaId(Equipe Equipe) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Equipe> query = session.createQuery("from Equipe s where s.id = :id", Equipe.class);
            query.setParameter("id", Equipe.getId());
 
            List<Equipe> queryList = query.getResultList();
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
    
    public Equipe EquipePelaId(int EquipeId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Equipe> query = session.createQuery("from Equipe s where s.id = :id", Equipe.class);
            query.setParameter("id", EquipeId);
 
            List<Equipe> queryList = query.getResultList();
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
 
    public List<Equipe> equipeVoluntario(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Equipe> query = session.createQuery("select s from Equipe s join s.membros m where m.id = :id", Equipe.class);
            query.setParameter("id", voluntario.getId());
 
            List<Equipe> queryList = query.getResultList();
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
    
    public void updateEquipe(Equipe Equipe) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Equipe);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Equipe addEquipe(Equipe Equipe) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Equipe);
            transaction.commit();
            return Equipe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
