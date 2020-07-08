package com.newer.task.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newer.task.data.pojo.TbEmployee;

//访问控制拦截器
public class AcessInterceptor implements HandlerInterceptor {
	// 这两个界面不需要拦截
	private static final String[] REQUEST_URL = { "/emp_input.do", "/emplogin",
			"/images/logo.jpg", "/images/button.jpg", "/css/css.css" };

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception excepton)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView excepton)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		boolean isFlag = false;
		String URL = request.getServletPath();// 获取到请求路径
		System.out.println("URL路径为" + URL);
		for (String requestURL : REQUEST_URL) {
			if (URL.contains(requestURL)) {
				// 存在不需要拦截的URL
				isFlag = true;
				break;
			}
		}
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		if (!isFlag) {
			// 如果是需要拦截的界面
			// 先判断session中是否存在用户

			if (employee != null) {
				// 如果存在用户
				isFlag = true;
			} else {
				// 不存在则拦截
				request.getRequestDispatcher("/emp_input.do").forward(request,
						response);
				isFlag = false;
			}
		}

		return isFlag;
	}

}
