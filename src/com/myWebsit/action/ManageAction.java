package com.myWebsit.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myWebsit.bean.Company;
import com.myWebsit.bean.Message;
import com.myWebsit.bean.News;
import com.myWebsit.bean.Pic;
import com.myWebsit.bean.Product;
import com.myWebsit.bean.Recruit;
import com.myWebsit.bean.User;
import com.myWebsit.dao.CompanyDao;
import com.myWebsit.dao.MessageDao;
import com.myWebsit.dao.NewsDao;
import com.myWebsit.dao.PicDao;
import com.myWebsit.dao.ProductDao;
import com.myWebsit.dao.RecruitDao;
import com.myWebsit.dao.UserDao;
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

	// 修改密码页面
	public String passwordChangePage() throws IOException {
		return "passwordChange";
	}

	// 登入请求
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
			response.getWriter()
					.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}

	// 用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}

	// 修改密码操作
	public void passwordChange() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '" + u.getUsername()
				+ "' and password= '" + password1 + "' ");
		if (bean != null) {
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response.getWriter()
					.print("<script language=javascript>alert('修改成功');window.location.href='manageAction!passwordChangePage.action';</script>");
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response.getWriter()
					.print("<script language=javascript>alert('原密码错误');window.location.href='manageAction!passwordChangePage.action';</script>");
		}
	}

	private PicDao picDao;

	public PicDao getPicDao() {
		return picDao;
	}

	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}

	// 首页图片列表
	public String picListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 order by id desc");
		String where = sb.toString();

		request.setAttribute("picList", picDao.selectBeanList(0, 9999, where));
		this.setUrl("picList.jsp");
		return SUCCESS;

	}

	// 更新首页图片页面
	public String picUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Pic pic = picDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("pic", pic);
		request.setAttribute("url",
				"manageAction!picUpdate.action?id=" + pic.getId());
		request.setAttribute("title", "首页图片信息修改");
		this.setUrl("picUpdate.jsp");
		return SUCCESS;
	}

	// 更新首页图片操作
	public void picUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		Pic pic = picDao.selectBean(" where id= " + request.getParameter("id"));
		pic.setInfo(info);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			pic.setPath(path);
		}

		picDao.updateBean(pic);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!picListPage.action';</script>");
	}

	private File uploadfile;

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	private CompanyDao companyDao;

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public RecruitDao getRecruitDao() {
		return recruitDao;
	}

	public void setRecruitDao(RecruitDao recruitDao) {
		this.recruitDao = recruitDao;
	}

	// 跳转到更新企业信息页面
	public String companyUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = null;
		List<Company> companyList = companyDao
				.selectBeanList(0, 1, "where 1=1");
		if (companyList != null && companyList.size() > 0) {
			company = companyList.get(0);
		}
		request.setAttribute("company", company);
		request.setAttribute("url", "manageAction!companyUpdate.action?id="
				+ company.getId());
		request.setAttribute("title", "企业信息修改");
		this.setUrl("companyUpdate.jsp");
		return SUCCESS;
	}

	// 更新公司信息
	public void companyUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fax = request.getParameter("fax");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		String contact = request.getParameter("contact");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		String mailbox = request.getParameter("mailbox");
		String name = request.getParameter("name");

		Company bean = companyDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setFax(fax);
		bean.setTel(tel);
		bean.setAddress(address);
		bean.setInfo(info);
		bean.setContact(contact);
		bean.setPhone(phone);
		bean.setPostcode(postcode);
		bean.setMailbox(mailbox);
		bean.setName(name);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String logo = time + ".jpg";
			File file1 = new File(savaPath + logo);
			Util.copyFile(uploadfile, file1);
			bean.setLogo(logo);
		}

		companyDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!companyUpdatePage.action';</script>");
	}

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 产品列表
	public String productListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String product_name = request.getParameter("product_name");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (product_name != null && !"".equals(product_name)) {

			sb.append("product_name like '%" + product_name + "%'");
			sb.append(" and ");
			request.setAttribute("product_name", product_name);
		}

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("productList", productDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!productListPage.action", "共有"
						+ total + "条记录"));
		request.setAttribute("url", "manageAction!productListPage.action");
		request.setAttribute("url2", "manageAction!product");
		request.setAttribute("title", "产品管理");
		this.setUrl("productList.jsp");
		return SUCCESS;

	}

	// 跳转到添加产品页面
	public String productAddPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "manageAction!productAdd.action");
		request.setAttribute("title", "产品添加");
		this.setUrl("productAdd.jsp");
		return SUCCESS;
	}

	// 添加产品操作
	public void productAdd() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		String product_name = request.getParameter("product_name");

		Product bean = new Product();
		bean.setInfo(info);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			bean.setPath(path);
		}
		bean.setProduct_name(product_name);
		bean.setIs_recommend("不推荐");
		bean.setCreated_time(Util.getTime());

		productDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!productListPage.action';</script>");
	}

	// 更新产品页面
	public String productUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product product = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("product", product);
		request.setAttribute("url", "manageAction!productUpdate.action?id="
				+ product.getId());
		request.setAttribute("title", "产品信息修改");
		this.setUrl("productUpdate.jsp");
		return SUCCESS;
	}

	// 更新产品操作
	public void productUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		String product_name = request.getParameter("product_name");
		Product product = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		product.setInfo(info);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			product.setPath(path);
		}
		product.setProduct_name(product_name);
		productDao.updateBean(product);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!productListPage.action';</script>");
	}

	// 删除产品操作
	public void productDelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));

		productDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!productListPage.action';</script>");
	}

	// 跳转到查看产品页面
	public String productDetailPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product product = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("product", product);
		request.setAttribute("url", "manageAction!productUpdatePage.action?id="
				+ product.getId());
		request.setAttribute("title", "产品信息查看");
		this.setUrl("productDetail.jsp");
		return SUCCESS;
	}

	// 推荐状态修改
	public void productRecommendChange() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		String flag = request.getParameter("flag");
		if ("0".equals(flag)) {
			bean.setIs_recommend("推荐");
		} else if ("1".equals(flag)) {
			bean.setIs_recommend("不推荐");
		}
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!productListPage.action';</script>");
	}

	private NewsDao newsDao;

	// 新闻列表
	public String newsListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (title != null && !"".equals(title)) {

			sb.append("title like '%" + title + "%'");
			sb.append(" and ");
			request.setAttribute("title", title);
		}

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = newsDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("newsList", newsDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!newsListPage.action", "共有" + total
						+ "条记录"));
		request.setAttribute("url", "manageAction!newsListPage.action");
		request.setAttribute("url2", "manageAction!news");
		request.setAttribute("title", "新闻管理");
		this.setUrl("newsList.jsp");
		return SUCCESS;

	}

	// 跳转到添加新闻页面
	public String newsAddPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "manageAction!newsAdd.action");
		request.setAttribute("title", "新闻添加");
		this.setUrl("newsAdd.jsp");
		return SUCCESS;
	}

	// 添加新闻操作
	public void newsAdd() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			news.setPicPath(path);
		}

		news.setIs_recommend("不推荐");
		news.setCreated_time(Util.getTime());

		newsDao.insertBean(news);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!newsListPage.action';</script>");
	}

	// 更新新闻页面
	public String newsUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		News news = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("news", news);
		request.setAttribute("url",
				"manageAction!newsUpdate.action?id=" + news.getId());
		request.setAttribute("title", "新闻信息修改");
		this.setUrl("newsUpdate.jsp");
		return SUCCESS;
	}

	// 更新新闻操作
	public void newsUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		News news = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		news.setTitle(title);
		news.setContent(content);
		if (uploadfile != null) {
			String savaPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "/uploadfile/";
			String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()).toString();

			String path = time + ".jpg";
			File file1 = new File(savaPath + path);
			Util.copyFile(uploadfile, file1);
			news.setPicPath(path);
		}
		newsDao.updateBean(news);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!newsListPage.action';</script>");
	}

	// 删除新闻操作
	public void newsDelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));

		newsDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!newsListPage.action';</script>");
	}

	// 查看新闻页面
	public String newsDetailPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		News news = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("news", news);
		request.setAttribute("url",
				"manageAction!newsAdd.action?id=" + news.getId());
		request.setAttribute("title", "新闻信息查看");
		this.setUrl("newsDetail.jsp");
		return SUCCESS;
	}

	// 改变新闻推荐状态
	public void newsRecommendChange() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		News bean = newsDao.selectBean(" where id= "
				+ request.getParameter("id"));
		String flag = request.getParameter("flag");
		if ("0".equals(flag)) {
			bean.setIs_recommend("推荐");
		} else if ("1".equals(flag)) {
			bean.setIs_recommend("不推荐");
		}
		newsDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!newsListPage.action';</script>");
	}

	private RecruitDao recruitDao;

	// 招聘列表
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
		int total = recruitDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("list", recruitDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!zhaopinlist.action", "共有" + total
						+ "条记录"));
		request.setAttribute("url", "manageAction!zhaopinlist.action");
		request.setAttribute("url2", "manageAction!zhaopin");
		request.setAttribute("title", "招聘管理");
		this.setUrl("zhaopinlist.jsp");
		return SUCCESS;

	}

	// 跳转到添加招聘页面
	public String zhaopinadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "manageAction!zhaopinadd2.action");
		request.setAttribute("title", "招聘添加");
		this.setUrl("zhaopinadd.jsp");
		return SUCCESS;
	}

	// 添加招聘操作
	public void zhaopinadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		String position = request.getParameter("position");

		Recruit bean = new Recruit();
		bean.setInfo(info);
		bean.setPosition(position);

		bean.setIs_recommend("未推荐");
		bean.setCreated_time(Util.getTime());

		recruitDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!zhaopinlist.action';</script>");
	}

	// 跳转到更新招聘页面
	public String zhaopinupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "manageAction!zhaopinupdate2.action?id="
				+ bean.getId());
		request.setAttribute("title", "招聘信息修改");
		this.setUrl("zhaopinupdate.jsp");
		return SUCCESS;
	}

	// 更新招聘操作
	public void zhaopinupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		String position = request.getParameter("position");
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setInfo(info);
		bean.setPosition(position);
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!zhaopinlist.action';</script>");
	}

	// 删除招聘操作
	public void zhaopindelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));

		recruitDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!zhaopinlist.action';</script>");
	}

	// 跳转到查看招聘页面
	public String zhaopinupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "manageAction!zhaopinupdate2.action?id="
				+ bean.getId());
		request.setAttribute("title", "招聘信息查看");
		this.setUrl("zhaopinupdate3.jsp");
		return SUCCESS;
	}

	// 推荐招聘操作
	public void zhaopindelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setIs_recommend("推荐");
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!zhaopinlist.action';</script>");
	}

	// 取消推荐招聘操作
	public void zhaopindelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setIs_recommend("未推荐");
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!zhaopinlist.action';</script>");
	}

	private MessageDao messageDao;

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	// 留言反馈列表
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
		int total = messageDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("list", messageDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!messagelist.action", "共有" + total
						+ "条记录"));
		request.setAttribute("url", "manageAction!messagelist.action");
		request.setAttribute("url2", "manageAction!message");
		request.setAttribute("title", "留言反馈管理");
		this.setUrl("messagelist.jsp");
		return SUCCESS;

	}

	// 删除留言反馈操作
	public void messagedelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));

		messageDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!messagelist.action';</script>");
	}

	// 跳转到查看留言反馈页面
	public String messageupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "manageAction!messageupdate2.action?id="
				+ bean.getId());
		request.setAttribute("title", "留言反馈信息查看");
		this.setUrl("messageupdate3.jsp");
		return SUCCESS;
	}

}
