package br.com.senac.mol.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;

public class DAO
{
	private static EntityManagerFactory emf = null;

	public EntityManager getEntityManager()
	{
		if (DAO.emf == null)
			DAO.emf = Persistence.createEntityManagerFactory("webmol");
		return DAO.emf.createEntityManager();
	}

	public Session getSession()
	{
		if (getEntityManager().getDelegate() instanceof EntityManagerImpl)
			return ((EntityManagerImpl) getEntityManager().getDelegate()).getSession();
		else
			return (Session) getEntityManager().getDelegate();
	}
}
