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
import com.mcx.model.InAndOut;
import com.mcx.service.IInAndOutService;

@Controller
@RequestMapping("/inAndOut")
public class InAndOutController extends BaseAction{

	@Autowired
	private IInAndOutService inAndOutService;
	/**
	 * 查看全部
	 * 
	 * @return
	 */
	@RequestMapping("/getInAndOutList")
	public String getInAndOutList(Model model, String stuName,
			String leaveTime, String returnTime) {
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(InAndOut.class);
		if (stuName != null && !stuName.equals("")) {
			dc.add(Restrictions.like("student.name", "%" + stuName + "%"));
		}
		if (leaveTime != null && !leaveTime.equals("")) {
			dc.add(Restrictions.like("leaveTime", "%" + leaveTime + "%"));
		}
		if (returnTime != null && !returnTime.equals("")) {
			dc.add(Restrictions.like("returnTime", "%" + returnTime + "%"));
		}
		List<InAndOut> inAndOutList = inAndOutService.findByCriteria(dc);
		model.addAttribute("inAndOutList", inAndOutList);
		model.addAttribute("stuName", stuName);
		model.addAttribute("leaveTime", leaveTime);
		model.addAttribute("returnTime", returnTime);
		model.addAttribute("mainPage", "/dormManage/inAndOutList.jsp");
		return "dormManage/index";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer inAndOutId) {
		InAndOut inAndOut = inAndOutService.getInAndOut(inAndOutId);
		model.addAttribute("inAndOut", inAndOut);
		model.addAttribute("mainPage", "/dormManage/inAndOutView.jsp");
		return "dormManage/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("inAndOut", new InAndOut());
		model.addAttribute("mainPage", "/dormManage/inAndOutView.jsp");
		return "dormManage/index";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, InAndOut inAndOut, Integer inAndOutId) {
		String message = "";
		if (inAndOutId == 0) {
			message = inAndOutService.save(inAndOut);
		} else {
			message = inAndOutService.update(inAndOut);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getInAndOutList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer inAndOutId) {
		String message = inAndOutService.delete(inAndOutId);
		attr.addFlashAttribute("message", message);
		return "redirect:getInAndOutList.action";
	}

}
