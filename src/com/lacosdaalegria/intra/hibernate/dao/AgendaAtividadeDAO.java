package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.AgendaAtividade;
import com.lacosdaalegria.intra.hibernate.model.Polo;

public class AgendaAtividadeDAO {
		
	public List<AgendaAtividade> listaAgendaAtividades() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade", AgendaAtividade.class);
            
            List<AgendaAtividade> queryList = query.getResultList();
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
	
	public List<AgendaAtividade> listaAgendaAtiva() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade where status = 1 and horario >= "
            		+ "DATEADD(day,-20, GETDATE())", AgendaAtividade.class);
            
            List<AgendaAtividade> queryList = query.getResultList();
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
	
	public List<AgendaAtividade> listaAgendaAtivaPolo(Polo polo) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade where instituicao.ra.polo.id = :id and status = 1 and horario >= "
            		+ "DATEADD(day,-32, GETDATE())", AgendaAtividade.class);
            query.setParameter("id", polo.getId());
            
            List<AgendaAtividade> queryList = query.getResultList();
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
	
	public List<AgendaAtividade> listaAgendaSemana() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade where horario <= "
            		+ "DATEADD(day,7, GETDATE()) and horario > GETDATE()", AgendaAtividade.class);
            
            List<AgendaAtividade> queryList = query.getResultList();
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
 
    public AgendaAtividade AgendaAtividadePelaId(AgendaAtividade agendaAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade s where s.id = :id", AgendaAtividade.class);
        query.setParameter("id", agendaAtividade.getId());
 
            List<AgendaAtividade> queryList = query.getResultList();
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
    
    public AgendaAtividade AgendaAtividadePelaId(int agenda_id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<AgendaAtividade> query = session.createQuery("from AgendaAtividade s where s.id = :id", AgendaAtividade.class);
        query.setParameter("id", agenda_id);
 
            List<AgendaAtividade> queryList = query.getResultList();
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
 
    public void updateAgendaAtividade(AgendaAtividade agendaAtividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(agendaAtividade);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
 
    public AgendaAtividade addAgendaAtividade(AgendaAtividade agendaAtividade) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(agendaAtividade);
            transaction.commit();
            return agendaAtividade;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	session.close();
        }
    }

}
