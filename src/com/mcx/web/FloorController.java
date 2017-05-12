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
import com.mcx.model.Floor;
import com.mcx.model.User;
import com.mcx.service.IFloorService;
import com.mcx.service.IUserService;

@Controller
@RequestMapping("/floor")
public class FloorController extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IFloorService floorService;

	/**
	 * 查询
	 */
	@RequestMapping("/getFloorList")
	public String getFloorList(Model model, String floorName) {
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Floor.class);
		if (floorName != null && !floorName.equals("")) {
			dc.add(Restrictions.like("floorName", "%" + floorName + "%"));
		}
		List<Floor> floorList = floorService.findByCriteria(dc);
		model.addAttribute("floorList", floorList);
		model.addAttribute("floorName", floorName);
		model.addAttribute("mainPage", "/floor/floorList.jsp");
		return "system/index";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Floor floor = floorService.getFloor(id);
		// 拼装查询条件，查询宿舍管理员
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("role", "manager"));
		List<User> userList = userService.findByCriteria(dc);
		model.addAttribute("userList", userList);
		model.addAttribute("floor", floor);
		model.addAttribute("mainPage", "/floor/floorView.jsp");
		return "system/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		// 拼装查询条件，查询宿舍管理员
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("role", "manager"));
		List<User> userList = userService.findByCriteria(dc);
		model.addAttribute("userList", userList);

		model.addAttribute("mainPage", "/floor/floorView.jsp");
		return "system/index";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, Floor floor, Integer id,
			Integer userId) {
		String message = "";
		if (userId != null) {
			User user = userService.getUser(userId);
			floor.setManager(user);
		}
		if (id == null) {
			message = floorService.save(floor);
		} else {
			message = floorService.update(floor);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getFloorList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer id) {
		Floor floor = floorService.getFloor(id);
		String message = "";
		if (floor != null) {
			message = floorService.delete(id);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getFloorList.action";
	}

}
