package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atendimento;
import com.lacosdaalegria.intra.hibernate.model.Categoria;

public class CategoriaDAO {
	
	public List<Categoria> listaCategoria() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria", Categoria.class);
 
            List<Categoria> queryList = query.getResultList();
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
	
	public List<Categoria> listaCategoriaGrupo(Atendimento atendimento) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria where atendimento = :id", Categoria.class);
            query.setParameter("id", atendimento);
 
            List<Categoria> queryList = query.getResultList();
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
	
	public List<Categoria> listaCategoriaAtivas() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria where status = 1", Categoria.class);
 
            List<Categoria> queryList = query.getResultList();
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
	
	
	public List<Categoria> listaCategoriasLivres() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria where atendimento is null", Categoria.class);
 
            List<Categoria> queryList = query.getResultList();
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
	
	
	
    public Categoria CategoriaPelaId(Categoria Categoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria s where s.id = :id", Categoria.class);
            query.setParameter("id", Categoria.getId());
 
            List<Categoria> queryList = query.getResultList();
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
    
    public Categoria CategoriaPelaId(int CategoriaId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Categoria> query = session.createQuery("from Categoria s where s.id = :id", Categoria.class);
            query.setParameter("id", CategoriaId);
 
            List<Categoria> queryList = query.getResultList();
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
    
    public void updateCategoria(Categoria Categoria) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Categoria);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Categoria addCategoria(Categoria Categoria) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Categoria);
            transaction.commit();
            return Categoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
