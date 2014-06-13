package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.Secao;

public class SecaoDAO extends DAO
{
	public void insert(Secao secao)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(secao);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(Secao secao)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			Secao trabalho = em.find(Secao.class, secao.getId());
			trabalho.setNome(secao.getNome());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(Secao secao)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Secao trabalho = em.find(Secao.class, secao.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Secao getById(long id)
	{
		Secao secao = null;
		EntityManager em = getEntityManager();
		try {
			secao = em.find(Secao.class, id);
		} finally {
			em.close();
		}
		return secao;
	}
}
