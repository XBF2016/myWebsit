package qiye.dao;

import java.util.List;

import qiye.model.Zhaopin;





public interface ZhaopinDao  {
	
	
	
	public void insertBean(Zhaopin bean);
	
	public void deleteBean(Zhaopin bean);
	
	public void updateBean(Zhaopin bean);

	public Zhaopin selectBean(String where);
	
	public List<Zhaopin> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
