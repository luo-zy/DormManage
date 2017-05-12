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
import com.mcx.model.Charge;
import com.mcx.service.IChargeService;

@Controller
@RequestMapping("/charge")
public class ChargeController extends BaseAction{

	@Autowired
	private IChargeService chargeService;
	/**
	 * 查看全部
	 * @param model
	 * @param message
	 * @param stuName学生
	 * @param item缴费项目
	 * @param time缴费时间
	 * @return
	 */
	@RequestMapping("/getChargeList")
	public String getChargeList(Model model,String stuName,String item,String time){
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Charge.class);
		//根据学生名称查询
		if (stuName != null && !stuName.equals("")) {
			dc.add(Restrictions.like("student.name", "%"+stuName+"%"));
		}
		// 根据缴费内容查
		if (item != null && !item.equals("")) {
			dc.add(Restrictions.eq("item", item));
		}
		// 根据缴费时间查询
		if (time != null && !time.equals("")) {
			dc.add(Restrictions.like("time", "%"+time+"%"));
		}
		List<Charge> chargeList = chargeService.findByCriteria(dc);
		model.addAttribute("chargeList", chargeList);
		model.addAttribute("stuName", stuName);
		model.addAttribute("item", item);
		model.addAttribute("time", time);
		model.addAttribute("mainPage", "/dormManage/chargeList.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model,Integer chargeId){
		Charge charge = chargeService.getCharge(chargeId);
		model.addAttribute("charge", charge);
		model.addAttribute("mainPage", "/dormManage/chargeView.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("charge",new Charge());
		model.addAttribute("mainPage", "/dormManage/chargeView.jsp");
		return "dormManage/index";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr,Charge charge,Integer chargeId) {
		String message = "";
		if(chargeId == 0){
			message = chargeService.save(charge);
		} else {
			message = chargeService.update(charge);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getChargeList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr,Integer chargeId) {
		String message = chargeService.delete(chargeId);
		attr.addFlashAttribute("message", message);
		return "redirect:getChargeList.action";
	}
	
}
