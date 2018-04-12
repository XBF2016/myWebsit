package com.myWebsit.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myWebsit.bean.Company;
import com.myWebsit.dao.CompanyDao;













public class CompanyDaoImpl extends HibernateDaoSupport implements  CompanyDao{


	public void deleteBean(Company bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Company bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Company selectBean(String where) {
		List<Company> list = this.getHibernateTemplate().find("from Qiye " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Qiye "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Company> selectBeanList(final int start,final int limit,final String where) {
		return (List<Company>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Company> list = session.createQuery("from Qiye "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Company bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
