package com.myWebsit.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;



import com.myWebsit.bean.Message;
import com.myWebsit.bean.Pic;
import com.myWebsit.bean.Product;
import com.myWebsit.bean.Company;
import com.myWebsit.bean.News;
import com.myWebsit.bean.Recruit;
import com.myWebsit.bean.Service;
import com.myWebsit.dao.MessageDao;
import com.myWebsit.dao.PicDao;
import com.myWebsit.dao.ProductDao;
import com.myWebsit.dao.CompanyDao;
import com.myWebsit.dao.NewsDao;
import com.myWebsit.dao.RecruitDao;
import com.myWebsit.dao.ServiceDao;
import com.myWebsit.util.Pager;
import com.myWebsit.util.Util;
import com.opensymphony.xwork2.ActionSupport;


public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;
	
	private String url = "./";
	
	private PicDao picDao;
	
	private CompanyDao companyDao;
	
	private ProductDao productDao;

	private NewsDao newsDao;
	
	private RecruitDao recruitDao;
	
	private ServiceDao serviceDao;


	//网站首页
	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if (request.getSession().getServletContext().getAttribute("company")==null) {
			//企业信息
			Company company = companyDao.selectBean(" where id=1 ");
			request.getSession().getServletContext().setAttribute("company", company);
		}
		if (request.getSession().getServletContext().getAttribute("picList")==null) {
			//图片信息
			List<Pic> picList = picDao.selectBeanList(0, 9999, "");
			request.getSession().getServletContext().setAttribute("picList", picList);
		}
		
		//推荐产品
		List<Product> recommendProductList = productDao.selectBeanList(0, 9999, " where is_recommend='推荐' order by id desc  ");
		request.setAttribute("recommendProductList", recommendProductList);
		//新闻列表
		List<News>newsList = newsDao.selectBeanList(0, 7, " order by is_recommend,id desc  ");
		request.setAttribute("newsList", newsList);
		//招聘信息
		List<Recruit>  recruitList = recruitDao.selectBeanList(0, 5, " order by is_recommend,id desc ");
		request.setAttribute("recruitList", recruitList);
		
		return "success";
	}
	
	
	
	//企业介绍页面
	public String aboutUsPage() {
		HttpServletRequest request = ServletActionContext.getRequest();

		this.setUrl("aboutUs.jsp");
		return SUCCESS;
	}
	
	//服务支持页面
	public String serviceSupportPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
       //销售和技术网点信息
		List<Service> serviceList=serviceDao.selectBeanList(0, 9999," where 1=1 ");
		request.setAttribute("serviceList", serviceList);
		this.setUrl("serviceSupport.jsp");
		return SUCCESS;
	}
	
	//网点详情页面
		public String serviceDetailPage() {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id=request.getParameter("id");
	       //销售和技术网点信息
			Service service=serviceDao.selectBean(" where id= "+id);
			request.setAttribute("service", service);
			this.setUrl("serviceDetail.jsp");
			return SUCCESS;
		}

	
	//产品展示页面
	public String productListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();

		
		String pname = request.getParameter("searchText");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pname != null && !"".equals(pname)) {

			sb.append("product_name like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", pname);
		}
		

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("productList", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexAction!productListPage.action", "共有" + total + "条记录"));
		
		
		this.setUrl("productList.jsp");
		return SUCCESS;
	}
	
	
	//产品介绍页面
	public String productPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = companyDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("company", company);
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("picList", picList);
		
		Product product = productDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("product", product);
		
		
		this.setUrl("product.jsp");
		return SUCCESS;
	}
	
	
	//新闻中心
	public String newsListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = companyDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("company", company);
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("picList", picList);

		
		String biaoti = request.getParameter("searchText");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (biaoti != null && !"".equals(biaoti)) {

			sb.append("title like '%" + biaoti + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", biaoti);
		}
		

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 9;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = newsDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("newsList", newsDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!xinwenlist.action", "共有" + total + "条记录"));
		
		this.setUrl("newsList.jsp");
		return SUCCESS;
	}
	
	
	
	//跳转到查看新闻页面
	public String newsPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = companyDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("company", company);
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("picList", picList);
		
		News news = newsDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("news", news);
		
		
		this.setUrl("news.jsp");
		return SUCCESS;
	}
	
	
	//招聘信息
	public String recruitListPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = companyDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("company", company);
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("picList", picList);

		
		String zhiwei = request.getParameter("searchText");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (zhiwei != null && !"".equals(zhiwei)) {

			sb.append("position like '%" + zhiwei + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", zhiwei);
		}
		

		sb.append("   1=1 order by is_recommend ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 9;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = recruitDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("recruitList", recruitDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!zhaopinlist.action", "共有" + total + "条记录"));
		
		this.setUrl("recruitList.jsp");
		return SUCCESS;
	}
	
	
	//跳转到查看招聘页面
	public String recruitPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//企业信息
		Company company = companyDao.selectBean(" where id=1 ");
		request.setAttribute("company", company);
		//图片信息
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");
		request.setAttribute("picList", picList);
		
		Recruit recruit = recruitDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("recruit", recruit);
		
		
		this.setUrl("recruit.jsp");
		return SUCCESS;
	}
	
	private MessageDao messageDao;



	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	
	//跳转到添加留言反馈页面
	public String feedbackPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Company company = companyDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("company", company);
		List<Pic> picList = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("picList", picList);
		
		this.setUrl("feedback.jsp");
		return SUCCESS;
	}
//添加留言反馈操作
	public void messageadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fax = request.getParameter("Guest_FAX");
		String tel = request.getParameter("Guest_TEL");
		String address = request.getParameter("Guest_ADD");
		String content = request.getParameter("Content");
		String name = request.getParameter("Guest_Name");
		String postcode = request.getParameter("Guest_ZIP");
		String mailbox = request.getParameter("Guest_Email");

		Message bean = new Message();
		
		bean.setFax(fax);
		bean.setTel(tel);
		bean.setAddress(address);
		bean.setContent(content);
		bean.setTime(Util.getTime());
		bean.setName(name);
		bean.setPostcode(postcode);
		bean.setMailbox(mailbox);
		
		
		messageDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功，请稍等，管理员会及时联系您');window.location.href='index.action';</script>");
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public PicDao getPicDao() {
		return picDao;
	}



	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}



	public CompanyDao getCompanyDao() {
		return companyDao;
	}



	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}



	public ProductDao getProductDao() {
		return productDao;
	}



	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
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



	public ServiceDao getServiceDao() {
		return serviceDao;
	}



	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	
	
}
