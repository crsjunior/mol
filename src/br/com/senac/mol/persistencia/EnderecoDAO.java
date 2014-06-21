package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;

import br.com.senac.mol.entidades.Endereco;

public class EnderecoDAO extends DAO {

    public void insert(Endereco localizacao) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(localizacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            //em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Endereco endereco) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Endereco trabalho = em.find(Endereco.class, endereco.getId());
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Endereco localizacao) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Endereco trabalho = em.find(Endereco.class, localizacao.getId());
            em.remove(trabalho);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Endereco getById(long id) {
        Endereco localizacao = null;
        EntityManager em = getEntityManager();
        try {
            localizacao = em.find(Endereco.class, id);
        } finally {
            em.close();
        }
        return localizacao;
    }

    public Endereco getByEndereco(long latitude, long longitude) {
        Endereco localizacao = null;
        EntityManager em = getEntityManager();
        try {
            Criteria criteria = getSession().createCriteria(Endereco.class);
            if (!criteria.list().isEmpty()) {
                localizacao = (Endereco) criteria.list().get(0);
            }
        } finally {
            em.close();
        }
        return localizacao;
    }
}
