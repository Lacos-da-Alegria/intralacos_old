package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Ouvidoria;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class OuvidoriaDAO {
	
	public List<Ouvidoria> listaOuvidoria() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria", Ouvidoria.class);
 
            List<Ouvidoria> queryList = query.getResultList();
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
	
	public List<Ouvidoria> meusAtendimentos(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria where voluntario = :vol and status != 2", Ouvidoria.class);
            query.setParameter("vol", voluntario);
 
            List<Ouvidoria> queryList = query.getResultList();
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
	
	public int meusAtendimentosAtendidos(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria where voluntario = :vol and status = 1", Ouvidoria.class);
            query.setParameter("vol", voluntario);
 
            List<Ouvidoria> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return 0;
            } else {
                return queryList.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }
	
    public Ouvidoria OuvidoriaPelaId(Ouvidoria Ouvidoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria s where s.id = :id", Ouvidoria.class);
            query.setParameter("id", Ouvidoria.getId());
 
            List<Ouvidoria> queryList = query.getResultList();
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
    
    public Ouvidoria OuvidoriaPelaId(int OuvidoriaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria s where s.id = :id", Ouvidoria.class);
            query.setParameter("id", OuvidoriaId);
 
            List<Ouvidoria> queryList = query.getResultList();
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
    
    public List<Ouvidoria> OuvidoriaPelaAtendimento(int atendId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Ouvidoria> query = session.createQuery("from Ouvidoria where categoria.atendimento.id = :id and status = 0", Ouvidoria.class);
            query.setParameter("id", atendId);
 
            List<Ouvidoria> queryList = query.getResultList();
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
    
    public void updateOuvidoria(Ouvidoria Ouvidoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Ouvidoria);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Ouvidoria addOuvidoria(Ouvidoria Ouvidoria) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Ouvidoria);
            transaction.commit();
            return Ouvidoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
