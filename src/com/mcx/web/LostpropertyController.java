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
import com.mcx.model.Lostproperty;
import com.mcx.service.ILostpropertyService;

@Controller
@RequestMapping("/lostproperty")
public class LostpropertyController extends BaseAction {
	@Autowired
	private ILostpropertyService lostpropertyService;
	
	/**
	 * 查看全部
	 * 
	 * @return
	 */
	@RequestMapping("/getLostpropertyList")
	public String getLostpropertyList(Model model, String stuName,
			String itemName, String claimSituation) {
		// 拼装查询条件(还应添加 按物品名称、按认领情况查询)
		DetachedCriteria dc = DetachedCriteria.forClass(Lostproperty.class);
		if (stuName != null && !stuName.equals("")) {
			dc.add(Restrictions.like("student.name", "%" + stuName + "%"));
		}
		if (itemName != null && !itemName.equals("")) {
			dc.add(Restrictions.like("itemName", "%" + itemName + "%"));
		}
		if (claimSituation != null && !claimSituation.equals("")) {
			dc.add(Restrictions.eq("claimSituation", claimSituation));
		}
		List<Lostproperty> lostpropertyList = lostpropertyService
				.findByCriteria(dc);
		model.addAttribute("lostpropertyList", lostpropertyList);
		model.addAttribute("stuName", stuName);
		model.addAttribute("itemName", itemName);
		model.addAttribute("claimSituation", claimSituation);
		model.addAttribute("mainPage", "/dormManage/lostpropertyList.jsp");
		return "dormManage/index";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer lostpropertyId) {
		Lostproperty lostproperty = lostpropertyService
				.getLostproperty(lostpropertyId);
		model.addAttribute("lostproperty", lostproperty);
		model.addAttribute("mainPage", "/dormManage/lostpropertyView.jsp");
		return "dormManage/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("lostproperty", new Lostproperty());
		model.addAttribute("mainPage", "/dormManage/lostpropertyView.jsp");
		return "dormManage/index";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, Lostproperty lostproperty,
			Integer lostpropertyId) {
		String message = "";
		if (lostpropertyId == 0) {
			message = lostpropertyService.save(lostproperty);
		} else {
			message = lostpropertyService.update(lostproperty);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getLostpropertyList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer lostpropertyId) {
		String message = lostpropertyService.delete(lostpropertyId);
		attr.addFlashAttribute("message", message);
		return "redirect:getLostpropertyList.action";
	}

}
