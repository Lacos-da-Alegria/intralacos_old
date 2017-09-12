package com.lacosdaalegria.intra.hibernate.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.lacosdaalegria.intra.hibernate.config.HibernateConnector;
import com.lacosdaalegria.intra.hibernate.model.Atendimento;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class VoluntarioDAO {
	
	public List<Voluntario> listaVoluntario() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario", Voluntario.class);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public List<Voluntario> listaAtendentes(Atendimento atendimento) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario where atendimento = :id", Voluntario.class);
            query.setParameter("id", atendimento);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public List<Voluntario> aniversariantes() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario where dt_Nascimento != 'Erro' and month(convert(datetime , dt_Nascimento, 103)) = MONTH(getdate()) and novato = false", Voluntario.class);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public List<Voluntario> listaDiretores() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario where diretoria is not null", Voluntario.class);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public List<Voluntario> equipeOngs() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario where polo is not null", Voluntario.class);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public List<Voluntario> novatosAtividade(Atividade atividade) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario v where v.novato is true "
            				+ "and v.preferencia = :id and v.status = 1 order by v.dt_criacao asc", Voluntario.class);
            query.setParameter("id", atividade);
            
            query.setMaxResults(30);
 
            List<Voluntario> queryList = query.getResultList();
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
	
	public Voluntario login(Voluntario voluntario) {
        Session session = null;
        
        voluntario.hashSenhas();
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario v where (v.login = :login or v.email = :email) and "
            		+ "senha = :senha", Voluntario.class);
            query.setParameter("senha", voluntario.getSenha());
            query.setParameter("email", voluntario.getLogin());
            query.setParameter("login", voluntario.getLogin());
 
            List<Voluntario> queryList = query.getResultList();
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
	
    public Voluntario VoluntarioPelaId(Voluntario Voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario s where s.id = :id", Voluntario.class);
            query.setParameter("id", Voluntario.getId());
 
            List<Voluntario> queryList = query.getResultList();
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
    
    public Voluntario voluntarioDuplicado(Voluntario voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario s where s.login = :login or"
            		+ " s.email = :email or s.cpf = :cpf or s.whatsapp = :whats", Voluntario.class);
            query.setParameter("login", voluntario.getLogin());
            query.setParameter("email", voluntario.getEmail());
            query.setParameter("cpf", voluntario.getCpf());
            query.setParameter("whats", voluntario.getWhatsapp());
 
            List<Voluntario> queryList = query.getResultList();
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
    
    public Voluntario VoluntarioPeloEmail(String email) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario s where s.email = :email", Voluntario.class);
            query.setParameter("email", email);
 
            List<Voluntario> queryList = query.getResultList();
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
    
    public Voluntario VoluntarioPelaId(int VoluntarioId) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario s where s.id = :id", Voluntario.class);
            query.setParameter("id", VoluntarioId);
 
            List<Voluntario> queryList = query.getResultList();
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
    
    public Voluntario atualizaPreferencia(Voluntario Voluntario, Atividade preferencia) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            TypedQuery<Voluntario> query = session.createQuery("from Voluntario s where s.id = :id", Voluntario.class);
            query.setParameter("id", Voluntario.getId());
 
            List<Voluntario> queryList = query.getResultList();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
            	queryList.get(0).setPreferencia(preferencia);
            	session.saveOrUpdate(queryList.get(0));
                transaction.commit();
                return queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Long posicaoNovato(Voluntario novato) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query<Long> query = session.createQuery("select count(*) from Voluntario v where v.status = 1 and v.novato is true and v.preferencia.id = :prefId "
            				+ "and v.dt_criacao < :id", Long.class);
            query.setParameter("id", novato.getDt_criacao());
            query.setParameter("prefId", novato.getPreferencia().getId());
 
            return query.uniqueResult() + 1;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public void updateVoluntario(Voluntario Voluntario) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Voluntario);
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Voluntario addVoluntario(Voluntario Voluntario) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(Voluntario);
            transaction.commit();
            return Voluntario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
