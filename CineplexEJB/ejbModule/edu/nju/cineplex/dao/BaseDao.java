package edu.nju.cineplex.dao;

import javax.ejb.Remote;

@Remote
public interface BaseDao {

	/**
	 * 根据主键查找
	 * @param c 对象类型
	 * @param id 主键
	 * @return 查找到的对象
	 */
	@SuppressWarnings("rawtypes")
	public Object find(Class c,int id);
	
	/**
	 * 更新对象
	 * @param m 对象类型
	 * @param obj 新的对象
	 */
	public <Model>void update(Model m,Object obj);
	
	/**
	 * 添加数据
	 * @param m 对象类型
	 * @param obj 新的对象
	 */
	public <Model>void add(Model m,Object obj);
	
}
