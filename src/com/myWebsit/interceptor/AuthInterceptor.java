package com.myWebsit.interceptor;

import java.util.HashSet;
import java.util.Set;

import com.myWebsit.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
	// 设置白名单
	private Set<String> path = new HashSet<String>();

	@Override
	// 初始化拦截名单
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		// 获取请求路径
		String actionName = actionContext.getName();

		// 白名单放行
		if (!actionName.contains("manage")
				|| actionName.equals("manageAction_login")) {
			return actionInvocation.invoke();
		}

		// 获取用户
		User user = (User) actionContext.getSession().get("user");

		// 如果不在白名单中又未登录，拦截
		if (user == null) {
			return "authError";
		}

		return actionInvocation.invoke();
	}
}
