package edu.nju.cineplex.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.nju.cineplex.dao.BaseDao;

@Stateless
public class BaseDaoImpl implements BaseDao {

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object find(Class c, int id) {
		// TODO Auto-generated method stub
		return em.find(c, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Model> void update(Model m, Object obj) {
		// TODO Auto-generated method stub
		em.merge((Model) obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Model> void add(Model m, Object obj) {
		// TODO Auto-generated method stub
		em.persist((Model) obj);
	}

}
