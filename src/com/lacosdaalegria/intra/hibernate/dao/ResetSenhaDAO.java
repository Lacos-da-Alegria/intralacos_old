package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.ResetSenha;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class ResetSenhaDAO {

	private List<ResetSenha> listaResetSenha(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<ResetSenha> query = session.createQuery("from ResetSenha where status = 1 and voluntario = :voluntario", ResetSenha.class);
            query.setParameter("voluntario", voluntario);
            
            List<ResetSenha> queryList = query.getResultList();
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
    
    public ResetSenha validaToken(String token) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<ResetSenha> query = session.createQuery("from ResetSenha s where s.token = :token and status = 1 and getdate() < DATEADD (hour,5,s.dt_criacao)", ResetSenha.class);
            query.setParameter("token", token);
 
            List<ResetSenha> queryList = query.getResultList();
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
    
    public void updateResetSenha(ResetSenha ResetSenha) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(ResetSenha);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ResetSenha addResetSenha(ResetSenha ResetSenha) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(ResetSenha);
            transaction.commit();
            return ResetSenha;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String criaTokenResetSenha(Voluntario voluntario){

    	List<ResetSenha> list = listaResetSenha(voluntario);
    	
    	if(list != null){
    	
	    	for(ResetSenha reset : list){
	    		reset.setStatus(2);
	    		updateResetSenha(reset);
	    	}
	    	
    	}
    	
    	ResetSenha newReset = new ResetSenha();
    	
    	newReset.setVoluntario(voluntario);
    	
    	return addResetSenha(newReset).getToken();
    }

    public void invalidaToken(String token){
    	ResetSenha reset = validaToken(token);
    	reset.setStatus(2);
    	updateResetSenha(reset);
    }
    
}
