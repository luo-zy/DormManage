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
import com.mcx.model.Discipline;
import com.mcx.service.IDisciplineService;

@Controller
@RequestMapping("/discipline")
public class DisciplineController extends BaseAction{
	
	@Autowired
	private IDisciplineService disciplineService;
	
	/**
	 * 查看全部
	 * 
	 * @return
	 */
	@RequestMapping("/getDisciplineList")
	public String getDisciplineList(Model model,String stuName) {
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Discipline.class);
		if (stuName != null && !stuName.equals("")) {
			dc.add(Restrictions.like("student.name", "%" + stuName + "%"));
		}
		List<Discipline> disciplineList = disciplineService.findByCriteria(dc);
		model.addAttribute("disciplineList", disciplineList);
		model.addAttribute("stuName", stuName);
		model.addAttribute("mainPage", "/dormManage/disciplineList.jsp");
		return "dormManage/index";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer disciplineId) {
		Discipline discipline = disciplineService.getDiscipline(disciplineId);
		model.addAttribute("discipline", discipline);
		model.addAttribute("mainPage", "/dormManage/disciplineView.jsp");
		return "dormManage/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("discipline", new Discipline());
		model.addAttribute("mainPage", "/dormManage/disciplineView.jsp");
		return "dormManage/index";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, Discipline discipline,
			Integer disciplineId) {
		String message = "";
		if (disciplineId == 0) {
			message = disciplineService.save(discipline);
		} else {
			message = disciplineService.update(discipline);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getDisciplineList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer disciplineId) {
		String message = disciplineService.delete(disciplineId);
		attr.addFlashAttribute("message", message);
		return "redirect:getDisciplineList.action";
	}

}
