package com.myWebsit.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myWebsit.bean.Service;
import com.myWebsit.dao.ServiceDao;

public class ServiceDaoImpl extends HibernateDaoSupport implements  ServiceDao{


	public void deleteBean(Service bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Service bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Service selectBean(String where) {
		List<Service> list = this.getHibernateTemplate().find("from Service " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Service "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Service> selectBeanList(final int start,final int limit,final String where) {
		return (List<Service>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Service> list = session.createQuery("from Service "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Service bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
