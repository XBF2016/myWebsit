package com.myWebsit.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myWebsit.bean.Recruit;
import com.myWebsit.dao.RecruitDao;













public class RecruitDaoImpl extends HibernateDaoSupport implements  RecruitDao{


	public void deleteBean(Recruit bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Recruit bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Recruit selectBean(String where) {
		List<Recruit> list = this.getHibernateTemplate().find("from Recruit " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Recruit "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Recruit> selectBeanList(final int start,final int limit,final String where) {
		return (List<Recruit>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Recruit> list = session.createQuery("from Recruit "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Recruit bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
