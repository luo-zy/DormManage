package com.mcx.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mcx.model.User;

/**
 * @Description 为控制器提供基础方法
 **/

@Controller
public class BaseAction {
	@Autowired
	private HttpServletRequest request;

	/**
	 * 获取离线查询体
	 */
	public <T> DetachedCriteria createDetachedCriteria(Class<T> entity) {
		return DetachedCriteria.forClass(entity);
	}

	/**
	 * 获取SESSION内的值
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(String attributeName) {
		return (T) request.getSession().getAttribute(attributeName);
	}

	/**
	 * 删除SESSION内的值
	 * 
	 * @param attributeName
	 */
	public void removeSessionAttribute(String attributeName) {
		request.getSession().removeAttribute(attributeName);
	}

	/**
	 * 获取当前用户
	 * 
	 * @param request
	 * @return
	 */
	public User getCurrentUser() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		return user;
	}

	/**
	 * 获取webPath 域名
	 */
	public String getWebPath() {
		return request.getContextPath();
	}

	/**
	 * 获取tomcat root目录
	 */
	public String getRootPath() {
		String ctx = request.getSession().getServletContext().getRealPath("");
		String rootPath = ctx.substring(0, ctx.lastIndexOf("\\"));
		return rootPath + "\\ROOT\\";
	}

	/**
	 * 获取ctxPath 项目路径
	 * 
	 * @return
	 */
	public String getCtxPath() {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 获取web目录的真实路径
	 */
	public String getFullRealPath(String webPath) {
		String frp = request.getSession().getServletContext()
				.getRealPath(webPath);
		// 检测目录是否存在
		File folder = new File(frp);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder.getAbsolutePath();
	}
}
