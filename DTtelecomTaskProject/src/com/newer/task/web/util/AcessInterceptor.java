package com.newer.task.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newer.task.data.pojo.TbEmployee;

//���ʿ���������
public class AcessInterceptor implements HandlerInterceptor {
	// ���������治��Ҫ����
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
		String URL = request.getServletPath();// ��ȡ������·��
		System.out.println("URL·��Ϊ" + URL);
		for (String requestURL : REQUEST_URL) {
			if (URL.contains(requestURL)) {
				// ���ڲ���Ҫ���ص�URL
				isFlag = true;
				break;
			}
		}
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		if (!isFlag) {
			// �������Ҫ���صĽ���
			// ���ж�session���Ƿ�����û�

			if (employee != null) {
				// ��������û�
				isFlag = true;
			} else {
				// ������������
				request.getRequestDispatcher("/emp_input.do").forward(request,
						response);
				isFlag = false;
			}
		}

		return isFlag;
	}

}
