package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.RegistroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Semana;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class RegistroAtividadeDAO {
	
	public List<RegistroAtividade> listaRegistroAtividade() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade", RegistroAtividade.class);
 
            List<RegistroAtividade> queryList = query.getResultList();
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
	
	public List<RegistroAtividade> listaRegistroAtividade(Voluntario voluntario, Semana semana) {
        Session session = null;
        
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade r where "
            				+ "r.status != 2 and r.semana = :semana and r.voluntario = :voluntario", RegistroAtividade.class);
            query.setParameter("voluntario", voluntario);
            query.setParameter("semana", semana);
 
            List<RegistroAtividade> queryList = query.getResultList();
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
	
	public List<RegistroAtividade> filaAtividade(Atividade atividade, Semana semana) {

		Session session = null;
        
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade r where "
            				+ "r.status != 2 and r.semana = :semana and r.atividade = :atividade "
            				+ "order by r.posicao asc, r.dt_criacao asc", RegistroAtividade.class);
            query.setParameter("atividade", atividade);
            query.setParameter("semana", semana);
            List<RegistroAtividade> queryList = query.getResultList();
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
	
	public List<RegistroAtividade> novatosAtividade(Atividade atividade, Semana semana) {
        Session session = null;
        
        
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade r where "
            				+ "r.status != 2 and r.semana = :semana and r.atividade = :atividade "
            				+ "and r.voluntario.novato is true", RegistroAtividade.class);
            
            query.setParameter("atividade", atividade);
            query.setParameter("semana", semana);
 
            List<RegistroAtividade> queryList = query.getResultList();
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
	
    public RegistroAtividade RegistroAtividadePelaId(RegistroAtividade RegistroAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade s where s.id = :id", RegistroAtividade.class);
            query.setParameter("id", RegistroAtividade.getId());
 
            List<RegistroAtividade> queryList = query.getResultList();
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
    
    public RegistroAtividade RegistroNovato(int idNovato) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade s "
            		+ "where s.voluntario.id = :id and s.status = 0", RegistroAtividade.class);
            query.setParameter("id", idNovato);
 
            List<RegistroAtividade> queryList = query.getResultList();
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
    
    public RegistroAtividade RegistroAtividadePelaId(int RegistroAtividadeId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<RegistroAtividade> query = session.createQuery("from RegistroAtividade s where s.id = :id", RegistroAtividade.class);
            query.setParameter("id", RegistroAtividadeId);
 
            List<RegistroAtividade> queryList = query.getResultList();
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
    
    public void updateRegistroAtividade(RegistroAtividade RegistroAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(RegistroAtividade);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public RegistroAtividade addRegistroAtividade(RegistroAtividade RegistroAtividade) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(RegistroAtividade);
            transaction.commit();
            return RegistroAtividade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
