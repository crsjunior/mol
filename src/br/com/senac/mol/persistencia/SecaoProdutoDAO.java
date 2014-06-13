package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.SecaoProduto;

public class SecaoProdutoDAO extends DAO
{
	public void insert(SecaoProduto secaoProduto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(secaoProduto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(SecaoProduto secaoProduto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			SecaoProduto trabalho = em.find(SecaoProduto.class, secaoProduto.getId());
			trabalho.setProduto(secaoProduto.getProduto());
			trabalho.setSecao(secaoProduto.getSecao());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(SecaoProduto secaoProduto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			SecaoProduto trabalho = em.find(SecaoProduto.class, secaoProduto.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public SecaoProduto getById(long id)
	{
		SecaoProduto secaoProduto = null;
		EntityManager em = getEntityManager();
		try {
			secaoProduto = em.find(SecaoProduto.class, id);
		} finally {
			em.close();
		}
		return secaoProduto;
	}
}
