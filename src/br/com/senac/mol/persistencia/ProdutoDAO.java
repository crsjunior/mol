package br.com.senac.mol.persistencia;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.Produto;

public class ProdutoDAO extends DAO
{
	public void insert(Produto produto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(Produto produto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			Produto trabalho = em.find(Produto.class, produto.getId());
			trabalho.setDescricao(produto.getDescricao());
			trabalho.setResenha(produto.getResenha());
			trabalho.setPreco(produto.getPreco());
			trabalho.setDataUltimaAtualizacao(new Date());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(Produto produto)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Produto trabalho = em.find(Produto.class, produto.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Produto getById(long id)
	{
		Produto produto = null;
		EntityManager em = getEntityManager();
		try {
			produto = em.find(Produto.class, id);
		} finally {
			em.close();
		}
		return produto;
	}
}
