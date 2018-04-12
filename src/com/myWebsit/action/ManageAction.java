package com.myWebsit.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.myWebsit.bean.Message;
import com.myWebsit.bean.Pic;
import com.myWebsit.bean.Product;
import com.myWebsit.bean.Company;
import com.myWebsit.bean.User;
import com.myWebsit.bean.News;
import com.myWebsit.bean.Recruit;
import com.myWebsit.dao.MessageDao;
import com.myWebsit.dao.PicDao;
import com.myWebsit.dao.ProductDao;
import com.myWebsit.dao.CompanyDao;
import com.myWebsit.dao.UserDao;
import com.myWebsit.dao.NewsDao;
import com.myWebsit.dao.RecruitDao;
import com.myWebsit.util.Pager;
import com.myWebsit.util.Util;
import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
//登入请求
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "'  ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}

//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' ");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');window.location.href='password.jsp';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='password.jsp';</script>");
		}
	}
	
	private PicDao picDao;

	public PicDao getPicDao() {
		return picDao;
	}

	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}

	//首页图片列表
	public String piclist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 order by id desc");
		String where = sb.toString();

		request.setAttribute("list", picDao.selectBeanList(0, 9999, where));
		this.setUrl("piclist.jsp");
		return SUCCESS;

	}

//跳转到更新首页图片页面
	public String picupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Pic bean = picDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!picupdate2.action?id="+bean.getId());
		request.setAttribute("title", "首页图片信息修改");
		this.setUrl("picupdate.jsp");
		return SUCCESS;
	}
//更新首页图片操作
	public void picupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		Pic bean = picDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setInfo(info);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath(
			"/")
			+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			bean.setPath(path);
		}
		
		picDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!piclist.action';</script>");
	}

	
	private File uploadfile;
	
	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
	
	private CompanyDao companyDao;

	public CompanyDao getQiyeDao() {
		return companyDao;
	}

	public void setQiyeDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
	
	
	
	//跳转到更新企业信息页面
	public String qiyeupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company bean = companyDao.selectBean(" where id= 1");
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!qiyeupdate2.action?id=1");
		request.setAttribute("title", "企业信息修改");
		this.setUrl("qiyeupdate.jsp");
		return SUCCESS;
	}
//更新首页图片操作
	public void qiyeupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chuanzhen = request.getParameter("chuanzhen");
		String dianhua = request.getParameter("dianhua");
		String dizhi = request.getParameter("dizhi");
		String jianjie = request.getParameter("jianjie");
		String lianxiren = request.getParameter("lianxiren");
		String shouji = request.getParameter("shouji");
		String youbian = request.getParameter("youbian");
		String youxiang = request.getParameter("youxiang");
		String mingchen = request.getParameter("mingchen");

		Company bean = companyDao.selectBean(" where id= "+ request.getParameter("id"));
		bean.setChuanzhen(chuanzhen);
		bean.setDianhua(dianhua);
		bean.setDizhi(dizhi);
		bean.setJianjie(jianjie);
		bean.setLianxiren(lianxiren);
		bean.setShouji(shouji);
		bean.setYoubian(youbian);
		bean.setYouxiang(youxiang);
		bean.setMingchen(mingchen);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath(
			"/")
			+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date()).toString();

			String logo = time + ".jpg";
			File file1 = new File(savaPath + logo);
			Util.copyFile(uploadfile, file1);
			bean.setLogo(logo);
		}
		
		companyDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!qiyeupdate.action';</script>");
	}
	
	
	
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	//产品列表
	public String productlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pname = request.getParameter("pname");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pname != null && !"".equals(pname)) {

			sb.append("pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("pname", pname);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!productlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!productlist.action");
		request.setAttribute("url2", "method!product");
		request.setAttribute("title", "产品管理");
		this.setUrl("productlist.jsp");
		return SUCCESS;

	}
//跳转到添加产品页面
	public String productadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!productadd2.action");
		request.setAttribute("title", "产品添加");
		this.setUrl("productadd.jsp");
		return SUCCESS;
	}
//添加产品操作
	public void productadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jieshao = request.getParameter("jieshao");
		String pname = request.getParameter("pname");

		Product bean = new Product();
		bean.setJieshao(jieshao);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath(
			"/")
			+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			bean.setPath(path);
		}
		bean.setPname(pname);
		bean.setTuijian("未推荐");
		bean.setCreatetime(Util.getTime());
		
		productDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!productlist.action';</script>");
	}
//跳转到更新产品页面
	public String productupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!productupdate2.action?id="+bean.getId());
		request.setAttribute("title", "产品信息修改");
		this.setUrl("productupdate.jsp");
		return SUCCESS;
	}
//更新产品操作
	public void productupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jieshao = request.getParameter("jieshao");
		String pname = request.getParameter("pname");
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setJieshao(jieshao);
		if(uploadfile!=null){
			String savaPath = ServletActionContext.getServletContext().getRealPath(
			"/")
			+ "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			bean.setPath(path);
		}
		bean.setPname(pname);
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!productlist.action';</script>");
	}
	//删除产品操作
	public void productdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		productDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!productlist.action';</script>");
	}
	
	
	//跳转到查看产品页面
	public String productupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!productupdate2.action?id="+bean.getId());
		request.setAttribute("title", "产品信息查看");
		this.setUrl("productupdate3.jsp");
		return SUCCESS;
	}
	
	
	//推荐产品操作
	public void productdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("推荐");
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!productlist.action';</script>");
	}
	
	//取消推荐产品操作
	public void productdelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("未推荐");
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!productlist.action';</script>");
	}
	
	
	private NewsDao newsDao;

	public NewsDao getXinwenDao() {
		return newsDao;
	}

	public void setXinwenDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	
	//新闻列表
	public String xinwenlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (biaoti != null && !"".equals(biaoti)) {

			sb.append("biaoti like '%" + biaoti + "%'");
			sb.append(" and ");
			request.setAttribute("biaoti", biaoti);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = newsDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", newsDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!xinwenlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!xinwenlist.action");
		request.setAttribute("url2", "method!xinwen");
		request.setAttribute("title", "新闻管理");
		this.setUrl("xinwenlist.jsp");
		return SUCCESS;

	}
//跳转到添加新闻页面
	public String xinwenadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!xinwenadd2.action");
		request.setAttribute("title", "新闻添加");
		this.setUrl("xinwenadd.jsp");
		return SUCCESS;
	}
//添加新闻操作
	public void xinwenadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		String neirong = request.getParameter("content1");

		News bean = new News();
		bean.setBiaoti(biaoti);
		bean.setNeirong(neirong);
		
		bean.setTuijian("未推荐");
		bean.setCreatetime(Util.getTime());
		
		newsDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!xinwenlist.action';</script>");
	}
//跳转到更新新闻页面
	public String xinwenupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!xinwenupdate2.action?id="+bean.getId());
		request.setAttribute("title", "新闻信息修改");
		this.setUrl("xinwenupdate.jsp");
		return SUCCESS;
	}
//更新新闻操作
	public void xinwenupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String biaoti = request.getParameter("biaoti");
		String neirong = request.getParameter("content1");
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setBiaoti(biaoti);
		bean.setNeirong(neirong);
		newsDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!xinwenlist.action';</script>");
	}
	//删除新闻操作
	public void xinwendelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		newsDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!xinwenlist.action';</script>");
	}
	
	
	//跳转到查看新闻页面
	public String xinwenupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!xinwenupdate2.action?id="+bean.getId());
		request.setAttribute("title", "新闻信息查看");
		this.setUrl("xinwenupdate3.jsp");
		return SUCCESS;
	}
	
	
	//推荐新闻操作
	public void xinwendelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("推荐");
		newsDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!xinwenlist.action';</script>");
	}
	
	//取消推荐新闻操作
	public void xinwendelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("未推荐");
		newsDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!xinwenlist.action';</script>");
	}
	
	
	private RecruitDao recruitDao;

	public RecruitDao getZhaopinDao() {
		return recruitDao;
	}

	public void setZhaopinDao(RecruitDao recruitDao) {
		this.recruitDao = recruitDao;
	}
	
	
	//招聘列表
	public String zhaopinlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String zhiwei = request.getParameter("zhiwei");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (zhiwei != null && !"".equals(zhiwei)) {

			sb.append("zhiwei like '%" + zhiwei + "%'");
			sb.append(" and ");
			request.setAttribute("zhiwei", zhiwei);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = recruitDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", recruitDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhaopinlist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!zhaopinlist.action");
		request.setAttribute("url2", "method!zhaopin");
		request.setAttribute("title", "招聘管理");
		this.setUrl("zhaopinlist.jsp");
		return SUCCESS;

	}
//跳转到添加招聘页面
	public String zhaopinadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!zhaopinadd2.action");
		request.setAttribute("title", "招聘添加");
		this.setUrl("zhaopinadd.jsp");
		return SUCCESS;
	}
//添加招聘操作
	public void zhaopinadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jieshao = request.getParameter("jieshao");
		String zhiwei = request.getParameter("zhiwei");

		Recruit bean = new Recruit();
		bean.setJieshao(jieshao);
		bean.setZhiwei(zhiwei);
		
		bean.setTuijian("未推荐");
		bean.setCreatetime(Util.getTime());
		
		recruitDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!zhaopinlist.action';</script>");
	}
//跳转到更新招聘页面
	public String zhaopinupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!zhaopinupdate2.action?id="+bean.getId());
		request.setAttribute("title", "招聘信息修改");
		this.setUrl("zhaopinupdate.jsp");
		return SUCCESS;
	}
//更新招聘操作
	public void zhaopinupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jieshao = request.getParameter("jieshao");
		String zhiwei = request.getParameter("zhiwei");
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setJieshao(jieshao);
		bean.setZhiwei(zhiwei);
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!zhaopinlist.action';</script>");
	}
	//删除招聘操作
	public void zhaopindelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		recruitDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!zhaopinlist.action';</script>");
	}
	
	
	//跳转到查看招聘页面
	public String zhaopinupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!zhaopinupdate2.action?id="+bean.getId());
		request.setAttribute("title", "招聘信息查看");
		this.setUrl("zhaopinupdate3.jsp");
		return SUCCESS;
	}
	
	
	//推荐招聘操作
	public void zhaopindelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("推荐");
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!zhaopinlist.action';</script>");
	}
	
	//取消推荐招聘操作
	public void zhaopindelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTuijian("未推荐");
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!zhaopinlist.action';</script>");
	}
	
	private MessageDao messageDao;

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	
	//留言反馈列表
	public String messagelist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		
		

		sb.append("   1=1 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = messageDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", messageDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!messagelist.action", "共有" + total + "条记录"));
		request.setAttribute("url", "method!messagelist.action");
		request.setAttribute("url2", "method!message");
		request.setAttribute("title", "留言反馈管理");
		this.setUrl("messagelist.jsp");
		return SUCCESS;

	}
	
	
	//删除留言反馈操作
	public void messagedelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		messageDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('success!');window.location.href='method!messagelist.action';</script>");
	}
	
	
	//跳转到查看留言反馈页面
	public String messageupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!messageupdate2.action?id="+bean.getId());
		request.setAttribute("title", "留言反馈信息查看");
		this.setUrl("messageupdate3.jsp");
		return SUCCESS;
	}
	
}
