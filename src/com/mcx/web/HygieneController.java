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
import com.mcx.model.Hygiene;
import com.mcx.service.IHygieneService;

@Controller
@RequestMapping("/hygiene")
public class HygieneController extends BaseAction {
	@Autowired
	private IHygieneService hygieneService;
	
	/**
	 * 查看全部
	 * @return
	 */
	@RequestMapping("/getHygieneList")
	public String getHygieneList(Model model,String stuName,String time){
		// 拼装查询条件()
		DetachedCriteria dc = DetachedCriteria.forClass(Hygiene.class);
		if (stuName != null && !stuName.equals("")) {
			dc.add(Restrictions.like("student.name", "%"+stuName+"%"));
		}
		if (time != null && !time.equals("")) {
			dc.add(Restrictions.like("time", "%"+time+"%"));
		}
		List<Hygiene> hygieneList = hygieneService.findByCriteria(dc);
		model.addAttribute("hygieneList", hygieneList);
		model.addAttribute("stuName", stuName);
		model.addAttribute("time", time);
		model.addAttribute("mainPage", "/dormManage/hygieneList.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model,Integer hygieneId){
		Hygiene hygiene = hygieneService.gethygiene(hygieneId);
		model.addAttribute("hygiene", hygiene);
		model.addAttribute("mainPage", "/dormManage/hygieneView.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("hygiene",new Hygiene());
		model.addAttribute("mainPage", "/dormManage/hygieneView.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr,Hygiene hygiene,Integer hygieneId) {
		String message = "";
		if(hygieneId == 0){
			message = hygieneService.save(hygiene);
		} else {
			message = hygieneService.update(hygiene);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getHygieneList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr,Integer hygieneId) {
		String message = hygieneService.delete(hygieneId);
		attr.addFlashAttribute("message", message);
		return "redirect:getHygieneList.action";
	}
	
}
