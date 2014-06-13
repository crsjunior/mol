package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.ItemLista;

public class ItemListaDAO extends DAO
{
	public void insert(ItemLista itemLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(itemLista);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(ItemLista itemLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			ItemLista trabalho = em.find(ItemLista.class, itemLista.getId());
			trabalho.setLista(itemLista.getLista());
			trabalho.setPreco(itemLista.getPreco());
			trabalho.setProduto(itemLista.getProduto());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(ItemLista itemLista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			ItemLista trabalho = em.find(ItemLista.class, itemLista.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public ItemLista getById(long id)
	{
		ItemLista itemLista = null;
		EntityManager em = getEntityManager();
		try {
			itemLista = em.find(ItemLista.class, id);
		} finally {
			em.close();
		}
		return itemLista;
	}
}
