package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import br.com.senac.mol.entidades.Localizacao;

public class LocalizacaoDAO extends DAO
{
	public void insert(Localizacao localizacao)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(localizacao);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Localizacao getById(long id)
	{
		Localizacao localizacao = null;
		EntityManager em = getEntityManager();
		try {
			localizacao = em.find(Localizacao.class, id);
		} finally {
			em.close();
		}
		return localizacao;
	}

	public Localizacao getByEndereco(long latitude, long longitude)
	{
		Localizacao localizacao = null;
		EntityManager em = getEntityManager();
		try {
			Criteria criteria = getSession().createCriteria(Localizacao.class);
			Criterion cr1 = Restrictions.eq("latitude", latitude);
			Criterion cr2 = Restrictions.eq("longitude", longitude);
			LogicalExpression exp = Restrictions.and(cr1, cr2);
			criteria.add(exp);
			if (!criteria.list().isEmpty())
				localizacao = (Localizacao) criteria.list().get(0);
		} finally {
			em.close();
		}
		return localizacao;
	}
}
