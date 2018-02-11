package qiye.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import qiye.dao.MessageDao;
import qiye.dao.PicDao;
import qiye.dao.ProductDao;
import qiye.dao.QiyeDao;
import qiye.dao.XinwenDao;
import qiye.dao.ZhaopinDao;
import qiye.model.Message;
import qiye.model.Pic;
import qiye.model.Product;
import qiye.model.Qiye;
import qiye.model.Xinwen;
import qiye.model.Zhaopin;
import qiye.util.Pager;
import qiye.util.Util;

import com.opensymphony.xwork2.ActionSupport;






public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;
	
	
	
	private String url = "./";
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	private PicDao picDao;
	
	private QiyeDao qiyeDao;
	
	
	
	
	public PicDao getPicDao() {
		return picDao;
	}

	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}

	public QiyeDao getQiyeDao() {
		return qiyeDao;
	}

	public void setQiyeDao(QiyeDao qiyeDao) {
		this.qiyeDao = qiyeDao;
	}

	
	private ProductDao productDao;
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private XinwenDao xinwenDao;
	
	public XinwenDao getXinwenDao() {
		return xinwenDao;
	}

	public void setXinwenDao(XinwenDao xinwenDao) {
		this.xinwenDao = xinwenDao;
	}
	
	private ZhaopinDao zhaopinDao;

	public ZhaopinDao getZhaopinDao() {
		return zhaopinDao;
	}

	public void setZhaopinDao(ZhaopinDao zhaopinDao) {
		this.zhaopinDao = zhaopinDao;
	}

	//网站首页
	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);
		
		
		List<Product> tjplist = productDao.selectBeanList(0, 9999, " where tuijian='推荐' order by id desc  ");//推荐产品
		request.setAttribute("tjplist", tjplist);
		
		List<Xinwen> xwlist = xinwenDao.selectBeanList(0, 7, " order by tuijian,id desc  ");//新闻列表
		request.setAttribute("xwlist", xwlist);
		
		
		List<Product> plist = productDao.selectBeanList(0, 8, "  order by id desc  ");//产品展示
		request.setAttribute("plist", plist);
		
		List<Zhaopin>  zlist = zhaopinDao.selectBeanList(0, 5, " order by tuijian,id desc ");//招聘信息
		request.setAttribute("zlist", zlist);
		
		return "success";
	}
	
	
	
	//跳转到关于我们页面
	public String single() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);

		this.setUrl("single.jsp");
		return SUCCESS;
	}
	

	
	//产品展示
	public String products() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);

		
		String pname = request.getParameter("searchtext");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pname != null && !"".equals(pname)) {

			sb.append("pname like '%" + pname + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", pname);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 15;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!products.action", "共有" + total + "条记录"));
		
		
		this.setUrl("products.jsp");
		return SUCCESS;
	}
	
	
	//跳转到查看产品介绍页面
	public String product() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);
		
		Product product = productDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("product", product);
		
		
		this.setUrl("product.jsp");
		return SUCCESS;
	}
	
	
	//新闻中心
	public String xinwenlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);

		
		String biaoti = request.getParameter("searchtext");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (biaoti != null && !"".equals(biaoti)) {

			sb.append("biaoti like '%" + biaoti + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", biaoti);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 9;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = xinwenDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", xinwenDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!xinwenlist.action", "共有" + total + "条记录"));
		
		this.setUrl("xinwenlist.jsp");
		return SUCCESS;
	}
	
	
	
	//跳转到查看新闻页面
	public String xinwen() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);
		
		Xinwen xinwen = xinwenDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("xinwen", xinwen);
		
		
		this.setUrl("xinwen.jsp");
		return SUCCESS;
	}
	
	
	
	//招聘信息
	public String zhaopinlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);

		
		String zhiwei = request.getParameter("searchtext");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (zhiwei != null && !"".equals(zhiwei)) {

			sb.append("zhiwei like '%" + zhiwei + "%'");
			sb.append(" and ");
			request.setAttribute("searchtext", zhiwei);
		}
		

		sb.append("   1=1 order by tuijian ,id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 9;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhaopinDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", zhaopinDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "indexmethod!zhaopinlist.action", "共有" + total + "条记录"));
		
		this.setUrl("zhaopinlist.jsp");
		return SUCCESS;
	}
	
	
	//跳转到查看招聘页面
	public String zhaopin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);
		
		Zhaopin zhaopin = zhaopinDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("zhaopin", zhaopin);
		
		
		this.setUrl("zhaopin.jsp");
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
	public String messageadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Qiye qiye = qiyeDao.selectBean(" where id=1 ");//企业信息
		request.setAttribute("qiye", qiye);
		List<Pic> piclist = picDao.selectBeanList(0, 9999, "");//图片信息
		request.setAttribute("piclist", piclist);
		
		this.setUrl("messageadd.jsp");
		return SUCCESS;
	}
//添加留言反馈操作
	public void messageadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chuanzhen = request.getParameter("Guest_FAX");
		String dianhua = request.getParameter("Guest_TEL");
		String dizhi = request.getParameter("Guest_ADD");
		String neirong = request.getParameter("Content");
		String xingming = request.getParameter("Guest_Name");
		String youbian = request.getParameter("Guest_ZIP");
		String youjianl = request.getParameter("Guest_Email");

		Message bean = new Message();
		
		bean.setChuanzhen(chuanzhen);
		bean.setDianhua(dianhua);
		bean.setDizhi(dizhi);
		bean.setNeirong(neirong);
		bean.setShijian(Util.getTime());
		bean.setXingming(xingming);
		bean.setYoubian(youbian);
		bean.setYoujianl(youjianl);
		
		
		messageDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功，请稍等，管理员会及时联系您');window.location.href='index.action';</script>");
	}
	
}
