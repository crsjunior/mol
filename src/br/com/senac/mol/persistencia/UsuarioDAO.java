package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import br.com.senac.mol.entidades.Usuario;

public class UsuarioDAO extends DAO
{
	public void insert(Usuario usuario)
	{
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Usuario getByEmailSenha(String email, String senha)
	{
		Usuario usuario = null;
		EntityManager em = getEntityManager();
		try {
			Criteria criteria = getSession().createCriteria(Usuario.class);
			Criterion cr1 = Restrictions.eq("email", email);
			Criterion cr2 = Restrictions.eq("senha", senha);
			LogicalExpression exp = Restrictions.and(cr1, cr2);
			criteria.add(exp);
			if (!criteria.list().isEmpty())
				usuario = (Usuario) criteria.list().get(0);
		} finally {
			em.close();
		}
		return usuario;
	}
}
