package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.MembroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class MembroAtividadeDAO {
	
	public List<MembroAtividade> listaMembroAtividade() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade", MembroAtividade.class);
 
            List<MembroAtividade> queryList = query.getResultList();
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
	
	public List<MembroAtividade> listaMembroAtividade(Atividade atividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade where atividade = :atividade", MembroAtividade.class);
            query.setParameter("atividade", atividade);
            
            List<MembroAtividade> queryList = query.getResultList();
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
	
	public List<MembroAtividade> listaApoios(Atividade atividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade where apoio is true and atividade = :atividade", MembroAtividade.class);
            query.setParameter("atividade", atividade);
            
            List<MembroAtividade> queryList = query.getResultList();
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
	
	
	public List<MembroAtividade> listaRecursos() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade where controle_novato is true or coordenador is true", MembroAtividade.class);
  
            List<MembroAtividade> queryList = query.getResultList();
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
	
    public MembroAtividade MembroAtividadePelaId(MembroAtividade MembroAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade s where s.id = :id", MembroAtividade.class);
            query.setParameter("id", MembroAtividade.getId());
 
            List<MembroAtividade> queryList = query.getResultList();
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
    
    public MembroAtividade MembroAtividadePelaId(int MembroAtividadeId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade s where s.id = :id", MembroAtividade.class);
            query.setParameter("id", MembroAtividadeId);
 
            List<MembroAtividade> queryList = query.getResultList();
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
    
    public MembroAtividade RetornaMembro(Voluntario membro) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade where membro = :id", MembroAtividade.class);
            query.setParameter("id", membro);
 
            List<MembroAtividade> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return addNovoMembro(membro);
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
    
    public MembroAtividade RetornaMembro(int membroId) {
    	Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<MembroAtividade> query = session.createQuery("from MembroAtividade where membro.id = :id", MembroAtividade.class);
            query.setParameter("id", membroId);
 
            List<MembroAtividade> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return addNovoMembro(membroId);
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
    
    private MembroAtividade addNovoMembro(Voluntario membro){
    	MembroAtividade membroAtiv = new MembroAtividade();
    	
    	membroAtiv.setMembro(membro);
    	
    	return addMembroAtividade(membroAtiv);
    }
    
    private MembroAtividade addNovoMembro(int membroId){
    	MembroAtividade membroAtiv = new MembroAtividade();
    	VoluntarioDAO volDao = new VoluntarioDAO();
    	
    	membroAtiv.setMembro(volDao.VoluntarioPelaId(membroId));
    	
    	return addMembroAtividade(membroAtiv);
    }
    
    public void updateMembroAtividade(MembroAtividade MembroAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(MembroAtividade);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public MembroAtividade addMembroAtividade(MembroAtividade MembroAtividade) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(MembroAtividade);
            transaction.commit();
            return MembroAtividade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
