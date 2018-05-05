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
import com.myWebsit.bean.Service;
import com.myWebsit.bean.User;
import com.myWebsit.dao.CompanyDao;
import com.myWebsit.dao.MessageDao;
import com.myWebsit.dao.NewsDao;
import com.myWebsit.dao.PicDao;
import com.myWebsit.dao.ProductDao;
import com.myWebsit.dao.RecruitDao;
import com.myWebsit.dao.ServiceDao;
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
					.print("<script language=javascript>alert('用户名或者密码错误');window.location.href='indexAction!loginPage.action';</script>");
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
					.print("<script language=javascript>alert('修改成功');window.location.href='indexAction!loginPage.action';</script>");
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
		String id = request.getParameter("id");
		Pic pic = picDao.selectBean(" where id= " + id);
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
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!picUpdatePage.action?id="
						+ id + "';</script>");
	}

	private File uploadfile;

	public File getUploadfile() {
		return uploadfile;
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
		String id = request.getParameter("id");
		String fax = request.getParameter("fax");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		String contact = request.getParameter("contact");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		String mailbox = request.getParameter("mailbox");
		String name = request.getParameter("name");

		Company bean = companyDao.selectBean(" where id= " + id);
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
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!companyUpdatePage.action?id="
						+ id + "';</script>");
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
		String id = request.getParameter("id");
		String info = request.getParameter("info");
		String product_name = request.getParameter("product_name");
		Product product = productDao.selectBean(" where id= " + id);
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
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!productUpdatePage.action?id="
						+ id + "';</script>");
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
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		News news = newsDao.selectBean(" where id= " + id);
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
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!newsUpdatePage.action?id="
						+ id + "';</script>");
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
	public String recruitListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String recruit = request.getParameter("recruit");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (recruit != null && !"".equals(recruit)) {

			sb.append("position like '%" + recruit + "%'");
			sb.append(" and ");
			request.setAttribute("recruit", recruit);
		}

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = recruitDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("recruitList", recruitDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!recruitListPage.action", "共有"
						+ total + "条记录"));
		request.setAttribute("url", "manageAction!recruitListPage.action");
		request.setAttribute("url2", "manageAction!recruit");
		request.setAttribute("title", "招聘管理");
		this.setUrl("recruitList.jsp");
		return SUCCESS;

	}

	// 跳转到添加招聘页面
	public String recruitAddPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "manageAction!recruitAdd.action");
		request.setAttribute("title", "招聘添加");
		this.setUrl("recruitAdd.jsp");
		return SUCCESS;
	}

	// 添加招聘操作
	public void recruitAdd() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String info = request.getParameter("info");
		String position = request.getParameter("position");

		Recruit bean = new Recruit();
		bean.setInfo(info);
		bean.setPosition(position);

		bean.setIs_recommend("不推荐");
		bean.setCreated_time(Util.getTime());

		recruitDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!recruitListPage.action';</script>");
	}

	// 跳转到更新招聘页面
	public String recruitUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit recruit = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("recruit", recruit);
		request.setAttribute("url", "manageAction!recruitUpdate.action?id="
				+ recruit.getId());
		request.setAttribute("title", "招聘信息修改");
		this.setUrl("recruitUpdate.jsp");
		return SUCCESS;
	}

	// 更新招聘操作
	public void recruitUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String info = request.getParameter("info");
		String position = request.getParameter("position");
		Recruit bean = recruitDao.selectBean(" where id= " + id);
		bean.setInfo(info);
		bean.setPosition(position);
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!recruitUpdatetPage.action?id="
						+ id + "';</script>");
	}

	// 删除招聘操作
	public void recruitDelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));

		recruitDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!recruitListPage.action';</script>");
	}

	// 跳转到查看招聘页面
	public String recruitDetailPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("recruit", bean);
		request.setAttribute("url", "manageAction!recruitDetail.action?id="
				+ bean.getId());
		request.setAttribute("title", "招聘信息查看");
		this.setUrl("recruitDetail.jsp");
		return SUCCESS;
	}

	// 推荐招聘操作
	public void recruitRecommendChange() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Recruit bean = recruitDao.selectBean(" where id= "
				+ request.getParameter("id"));
		String flag = request.getParameter("flag");
		if ("0".equals(flag)) {
			bean.setIs_recommend("推荐");
		} else if ("1".equals(flag)) {
			bean.setIs_recommend("不推荐");
		}
		recruitDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!recruitListPage.action';</script>");
	}

	private MessageDao messageDao;

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	// 留言反馈列表
	public String messageListPage() {
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
		request.setAttribute("messageList", messageDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!messageListPage.action", "共有"
						+ total + "条记录"));
		request.setAttribute("url", "manageAction!messageListPage.action");
		request.setAttribute("url2", "manageAction!message");
		request.setAttribute("title", "留言反馈管理");
		this.setUrl("messageList.jsp");
		return SUCCESS;

	}

	// 删除留言反馈操作
	public void messageDelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));

		messageDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!messageListPage.action';</script>");
	}

	// 跳转到查看留言反馈页面
	public String messageDetailPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Message bean = messageDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("message", bean);
		request.setAttribute("url", "manageAction!messageDetail.action?id="
				+ bean.getId());
		request.setAttribute("title", "留言反馈信息查看");
		this.setUrl("messageDetail.jsp");
		return SUCCESS;
	}

	private ServiceDao serviceDao;

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	// 网点列表
	public String serviceListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String name = request.getParameter("name");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (name != null && !"".equals(name)) {

			sb.append("name like '%" + name + "%'");
			sb.append(" and ");
			request.setAttribute("name", name);
		}

		sb.append("   1=1 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = serviceDao.selectBeanCount(where.replaceAll(
				" order by id desc ", ""));
		request.setAttribute("serviceList", serviceDao.selectBeanList(
				(currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "manageAction!serviceListPage.action", "共有"
						+ total + "条记录"));
		request.setAttribute("url", "manageAction!serviceListPage.action");
		request.setAttribute("url2", "manageAction!service");
		request.setAttribute("title", "网点管理");
		this.setUrl("serviceList.jsp");
		return SUCCESS;

	}

	// 跳转到添加网点页面
	public String serviceAddPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "manageAction!serviceAdd.action");
		request.setAttribute("title", "网点添加");
		this.setUrl("serviceAdd.jsp");
		return SUCCESS;
	}

	// 添加网点信息
	public void serviceAdd() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fax = request.getParameter("fax");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		String mailbox = request.getParameter("mailbox");
		String name = request.getParameter("name");

		Service bean = new Service();

		bean.setFax(fax);
		bean.setTel(tel);
		bean.setAddress(address);
		bean.setContact(contact);
		bean.setPhone(phone);
		bean.setPostcode(postcode);
		bean.setMailbox(mailbox);
		bean.setName(name);

		serviceDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!serviceListPage.action';</script>");
	}

	// 跳转到查看产品页面
	public String serviceDetailPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Service service = serviceDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("service", service);
		request.setAttribute("url", "manageAction!serviceUpdatePage.action?id="
				+ service.getId());
		request.setAttribute("title", "产品信息查看");
		this.setUrl("serviceDetail.jsp");
		return SUCCESS;
	}

	// 更新网点页面
	public String serviceUpdatePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Service service = serviceDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("service", service);
		request.setAttribute("url", "manageAction!serviceUpdate.action?id="
				+ service.getId());
		request.setAttribute("title", "网点信息修改");
		this.setUrl("serviceUpdate.jsp");
		return SUCCESS;
	}

	// 更新网点信息
	public void serviceUpdate() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String id = request.getParameter("id");

		String fax = request.getParameter("fax");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String phone = request.getParameter("phone");
		String postcode = request.getParameter("postcode");
		String mailbox = request.getParameter("mailbox");
		String name = request.getParameter("name");

		Service bean = serviceDao.selectBean(" where id= " + id);

		bean.setFax(fax);
		bean.setTel(tel);
		bean.setAddress(address);
		bean.setContact(contact);
		bean.setPhone(phone);
		bean.setPostcode(postcode);
		bean.setMailbox(mailbox);
		bean.setName(name);

		serviceDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		response.getWriter()
				.print("<script language=javascript>alert('success!');window.location.href='manageAction!serviceUpdatePage.action?id="
						+ id + "';</script>");
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

}
