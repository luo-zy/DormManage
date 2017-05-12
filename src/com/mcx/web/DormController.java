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
import com.mcx.model.Floor;
import com.mcx.service.IDormService;
import com.mcx.service.IFloorService;

@Controller
@RequestMapping("/dorm")
public class DormController extends BaseAction{
	
	@Autowired
	private IDormService dormService;
	
	@Autowired
	private IFloorService floorService;
	/**
	 * 查看全部
	 * @param request
	 * @param floorId
	 * @param message
	 * @return
	 */
	@RequestMapping("/getDormList")
	public String getDormList(Model model,String dormName,Integer floorId){
		// 拼装查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Dorm.class);
		if (dormName != null && !dormName.equals("")) {
			dc.add(Restrictions.like("dormName", "%"+dormName+"%"));
		}
		if (floorId != null) {
			dc.add(Restrictions.eq("floor.id", floorId));
		}
		List<Dorm> dormList = dormService.findByCriteria(dc);
		model.addAttribute("dormList", dormList);
		model.addAttribute("floorList", floorService.findByCriteria(DetachedCriteria.forClass(Floor.class)));
		model.addAttribute("dormName", dormName);
		model.addAttribute("floorId", floorId);
		model.addAttribute("mainPage", "/dorm/dormList.jsp");
		return "system/index";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model,Integer id,Integer floorId){
		Dorm dorm = dormService.getDorm(id);
		model.addAttribute("dorm", dorm);
		model.addAttribute("floorList", floorService.findByCriteria(DetachedCriteria.forClass(Floor.class)));
		model.addAttribute("mainPage", "/dorm/dormView.jsp");
		return "system/index";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model,Integer floorId) {
		model.addAttribute("floorList", floorService.findByCriteria(DetachedCriteria.forClass(Floor.class)));
		model.addAttribute("mainPage", "/dorm/dormView.jsp");
		return "system/index";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr,Dorm dorm,Integer id,Integer floorId) {
		String message = "";
		if(floorId != null){
			Floor floor = floorService.getFloor(floorId);
			dorm.setFloor(floor);
		}
		if(id == null){
			message = dormService.save(dorm);
		} else {
			message = dormService.update(dorm);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getDormList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr,Integer id) {
		String message = dormService.delete(id);
		attr.addFlashAttribute("message", message);
		return "redirect:getDormList.action";
	}
	
}
