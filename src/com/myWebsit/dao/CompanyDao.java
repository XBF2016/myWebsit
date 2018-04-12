package com.myWebsit.dao;

import java.util.List;

import com.myWebsit.bean.Company;






public interface CompanyDao  {
	
	
	
	public void insertBean(Company bean);
	
	public void deleteBean(Company bean);
	
	public void updateBean(Company bean);

	public Company selectBean(String where);
	
	public List<Company> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
