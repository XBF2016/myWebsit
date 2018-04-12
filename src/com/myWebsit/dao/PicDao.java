package com.myWebsit.dao;

import java.util.List;

import com.myWebsit.bean.Pic;






public interface PicDao  {
	
	
	
	public void insertBean(Pic bean);
	
	public void deleteBean(Pic bean);
	
	public void updateBean(Pic bean);

	public Pic selectBean(String where);
	
	public List<Pic> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
