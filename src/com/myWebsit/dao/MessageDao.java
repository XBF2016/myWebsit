package com.myWebsit.dao;

import java.util.List;

import com.myWebsit.bean.Message;






public interface MessageDao  {
	
	
	
	public void insertBean(Message bean);
	
	public void deleteBean(Message bean);
	
	public void updateBean(Message bean);

	public Message selectBean(String where);
	
	public List<Message> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
