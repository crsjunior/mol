package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.PrecoLista;

public class PrecoListaDAO extends DAO
{
	public void insert(PrecoLista precoLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(precoLista);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(PrecoLista precoLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			PrecoLista trabalho = em.find(PrecoLista.class, precoLista.getId());
			trabalho.setPreco(precoLista.getPreco());
			trabalho.setItemLista(precoLista.getItemLista());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(PrecoLista precoLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			PrecoLista trabalho = em.find(PrecoLista.class, precoLista.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public PrecoLista getById(long id)
	{
		PrecoLista precoLista = null;
		EntityManager em = getEntityManager();
		try {
			precoLista = em.find(PrecoLista.class, id);
		} finally {
			em.close();
		}
		return precoLista;
	}
}
