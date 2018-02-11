package qiye.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import qiye.dao.ZhaopinDao;
import qiye.model.Zhaopin;












public class ZhaopinDaoImpl extends HibernateDaoSupport implements  ZhaopinDao{


	public void deleteBean(Zhaopin bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Zhaopin bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Zhaopin selectBean(String where) {
		List<Zhaopin> list = this.getHibernateTemplate().find("from Zhaopin " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zhaopin "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zhaopin> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zhaopin>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zhaopin> list = session.createQuery("from Zhaopin "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zhaopin bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
