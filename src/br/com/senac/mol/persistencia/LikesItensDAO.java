package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import br.com.senac.mol.entidades.LikesItens;

public class LikesItensDAO extends DAO
{
	public void insert(LikesItens likesItens)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(likesItens);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void update(LikesItens likesItens)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			LikesItens trabalho = em.find(LikesItens.class, likesItens.getId());
			trabalho.setItemLista(likesItens.getItemLista());
			trabalho.setUsuario(likesItens.getUsuario());
			trabalho.setLk(likesItens.getLk());

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(LikesItens likesItens)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			LikesItens trabalho = em.find(LikesItens.class, likesItens.getId());
			em.remove(trabalho);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public LikesItens getById(long id)
	{
		LikesItens likesItens = null;
		EntityManager em = getEntityManager();
		try {
			likesItens = em.find(LikesItens.class, id);
		} finally {
			em.close();
		}
		return likesItens;
	}
}
