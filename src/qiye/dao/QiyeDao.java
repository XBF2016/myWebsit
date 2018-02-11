package qiye.dao;

import java.util.List;

import qiye.model.Qiye;





public interface QiyeDao  {
	
	
	
	public void insertBean(Qiye bean);
	
	public void deleteBean(Qiye bean);
	
	public void updateBean(Qiye bean);

	public Qiye selectBean(String where);
	
	public List<Qiye> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
