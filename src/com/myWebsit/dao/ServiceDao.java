package com.myWebsit.dao;

import java.util.List;

import com.myWebsit.bean.Company;
import com.myWebsit.bean.Service;

public interface ServiceDao  {
	
	public void insertBean(Service bean);
	
	public void deleteBean(Service bean);
	
	public void updateBean(Service bean);

	public Service selectBean(String where);
	
	public List<Service> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
