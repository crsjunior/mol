package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.Estabelecimento;
import java.util.List;
import javax.persistence.Query;

public class EstabelecimentoDAO extends DAO {

    public void insert(Estabelecimento estabelecimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(estabelecimento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Estabelecimento estabelecimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Estabelecimento trabalho = em.find(Estabelecimento.class, estabelecimento.getId());
            trabalho.setNome(estabelecimento.getNome());
            trabalho.setEndereco(estabelecimento.getEndereco());

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Estabelecimento estabelecimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Estabelecimento trabalho = em.find(Estabelecimento.class, estabelecimento.getId());
            em.remove(trabalho);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Estabelecimento> getEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = null;
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM Estabelecimento a ORDER BY a.nome DESC");
            estabelecimentos = query.getResultList();
        } finally {
            em.close();
        }
        return estabelecimentos;
    }

    public Estabelecimento getById(long id) {
        Estabelecimento estabelecimento = null;
        EntityManager em = getEntityManager();
        try {
            estabelecimento = em.find(Estabelecimento.class, id);
        } finally {
            em.close();
        }
        return estabelecimento;
    }
}
