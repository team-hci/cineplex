package edu.nju.cineplex.dao;

import javax.ejb.Remote;

@Remote
public interface BaseDao {

	/**
	 * ������������
	 * @param c ��������
	 * @param id ����
	 * @return ���ҵ��Ķ���
	 */
	@SuppressWarnings("rawtypes")
	public Object find(Class c,int id);
	
	/**
	 * ���¶���
	 * @param m ��������
	 * @param obj �µĶ���
	 */
	public <Model>void update(Model m,Object obj);
	
	/**
	 * �������
	 * @param m ��������
	 * @param obj �µĶ���
	 */
	public <Model>void add(Model m,Object obj);
	
}
