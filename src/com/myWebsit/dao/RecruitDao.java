package com.myWebsit.dao;

import java.util.List;

import com.myWebsit.bean.Recruit;






public interface RecruitDao  {
	
	
	
	public void insertBean(Recruit bean);
	
	public void deleteBean(Recruit bean);
	
	public void updateBean(Recruit bean);

	public Recruit selectBean(String where);
	
	public List<Recruit> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
