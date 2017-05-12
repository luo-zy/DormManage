package com.mcx.web;

import java.util.List;






import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mcx.common.BaseAction;
import com.mcx.model.Dorm;
import com.mcx.model.User;
import com.mcx.service.IDormService;
import com.mcx.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseAction {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IDormService dormService;
	
	@RequestMapping("/getUserList")
	public String getUserList(Model model,String name, String role, Integer dormId) {
		User user = getCurrentUser();
		if(user.getRole().equals("manager")){
			role = "student";
		}
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (name != null && !name.equals("")) {
			dc.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (role != null && !role.equals("")) {
			dc.add(Restrictions.eq("role", role));
		}
		List<User> userList = userService.findByCriteria(dc);

		model.addAttribute("userList", userList);
		model.addAttribute("role", role);
		model.addAttribute("name", name);
		model.addAttribute("mainPage", "/user/userList.jsp");
		return "system/index";
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	public String update(Model model,Integer id) {
		User user = userService.getUser(id);
		String role = user.getRole();
		if(role.equals("student")){
			model.addAttribute("dormList", dormService.findByCriteria(DetachedCriteria.forClass(Dorm.class)));
		}
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		model.addAttribute("mainPage", "/user/userView.jsp");
		return "system/index";
	}

	/**
	 * 添加用户
	 */
	@RequestMapping("/create")
	public String create(Model model, String role) {
		if(role.equals("student")){
			model.addAttribute("dormList", dormService.findByCriteria(DetachedCriteria.forClass(Dorm.class)));
		}
		model.addAttribute("role", role);
		model.addAttribute("mainPage", "/user/userView.jsp");
		return "system/index";
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, User user,Integer dormId, Integer id) {
		String message = "";
		if(dormId != null){
			Dorm dorm = dormService.getDorm(dormId);
			user.setDorm(dorm);
		}
		if (id == null) {
			message = userService.save(user);
		} else {
			message = userService.update(user);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getUserList.action";
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer id, String role) {
		String message = userService.delete(id);
		attr.addFlashAttribute("message", message);
		return "redirect:getUserList.action";
	}
	
	
	/**
	 * 到个人信息页
	 * @param model
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String userInfo(Model model,Integer id) {
		User user = userService.getUser(id);
		String role = user.getRole();
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		model.addAttribute("mainPage", "/user/userInfo.jsp");
		return "system/index";
	}
	
	/**
	 * 个人信息保存
	 * @param attr
	 * @param user
	 * @return
	 */
	@RequestMapping("/userInfoSave")
	public String userInfoSave(RedirectAttributes attr, User user) {
		userService.update(user);
		attr.addFlashAttribute("message", "个人信息修改成功！");
		return "redirect:userInfo.action?id="+user.getId();
	}

}