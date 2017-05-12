package com.mcx.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcx.common.BaseAction;
import com.mcx.model.User;
import com.mcx.service.IUserService;

@Controller
@RequestMapping
public class SystemController extends BaseAction {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IUserService userService;

	/**
	 * 到主页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, String mainPage) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		String url = "/system/login";
		if (user != null) {
			url = "/system/index";
			model.addAttribute("role", user.getRole());
		}
		if (mainPage == null || mainPage.equals("")) {
			mainPage = "/system/Welcome.jsp";
		}
		model.addAttribute("mainPage", mainPage);
		return url;
	}

	/*
	 * 到登录页
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/system/login";
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/userLogin")
	public String userLogin(Model model, String username, String password,
			String role) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		List<User> users = userService.findByCriteria(dc);
		boolean reg = false;
		String message = "";
		if (users.size() > 0) {
			for (User user : users) {
				if(user.getPassword().equals(password)){
					if(user.getRole().equals(role)){
						session.setAttribute("currentUserRole", user.getRole());
						session.setAttribute("currentUser", user);
						reg = true;
					} else {
						message = "角色不正确！";
					}
				} else {
					message = "密码错误！";
				}
				break;
			}
		} else {
			message = "用户名不存在！";
		}
		if(reg){
			return index(model, null);
		} else {
			model.addAttribute("message", message);
			model.addAttribute("username", username);
			return toLogin();
		}
	}

	/**
	 * 注销登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(Model model) {
		HttpSession session = request.getSession();
		session.removeAttribute("currentUserRole");
		session.removeAttribute("currentUser");
		return toLogin();
	}

	@RequestMapping("/toPasswordUpdate")
	public String toPasswordUpdate(Model model) {
		return index(model, "/system/passwordUpdate.jsp");
	}

	@RequestMapping("/passwordUpdate")
	public String passwordUpdate(Model model, Integer id,String oldPassword, String newPassword) {
		User user = getCurrentUser();
		String message = "";
		if(user.getPassword().trim().equals(oldPassword)){
			user.setPassword(newPassword);
			userService.update(user);
			message = "密码修改成功！";
		} else {
			message = "旧密码不正确！";
		}
		model.addAttribute("message", message);
		return toPasswordUpdate(model);
	}

}