package qiye.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import qiye.dao.QiyeDao;
import qiye.model.Qiye;












public class QiyeDaoImpl extends HibernateDaoSupport implements  QiyeDao{


	public void deleteBean(Qiye bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Qiye bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Qiye selectBean(String where) {
		List<Qiye> list = this.getHibernateTemplate().find("from Qiye " +where);
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
	public List<Qiye> selectBeanList(final int start,final int limit,final String where) {
		return (List<Qiye>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Qiye> list = session.createQuery("from Qiye "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Qiye bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
