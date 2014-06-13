package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.Lista;

public class ListaDAO extends DAO
{
	public void insert(Lista lista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(lista);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(Lista lista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			Lista trabalho = em.find(Lista.class, lista.getId());
			trabalho.setDescricao(lista.getDescricao());
			trabalho.setUsuario(lista.getUsuario());
			trabalho.setEstabelecimento(lista.getEstabelecimento());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(Lista lista)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Lista trabalho = em.find(Lista.class, lista.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Lista getById(long id)
	{
		Lista lista = null;
		EntityManager em = getEntityManager();
		try {
			lista = em.find(Lista.class, id);
		} finally {
			em.close();
		}
		return lista;
	}
}
