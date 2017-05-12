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
import com.mcx.model.Notice;
import com.mcx.service.INoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseAction {

	@Autowired
	private INoticeService noticeService;

	/**
	 * 查看全部
	 * 
	 * @return
	 */
	@RequestMapping("/getNoticeList")
	public String getNoticeList(Model model, String noticeTitle,
			String noticeTime) {
		// 拼装查询条件(修改为按时间查询)
		DetachedCriteria dc = DetachedCriteria.forClass(Notice.class);
		if (noticeTitle != null && !noticeTitle.equals("")) {
			dc.add(Restrictions.like("noticeTitle", "%" + noticeTitle + "%"));
		}
		if (noticeTime != null && !noticeTime.equals("")) {
			dc.add(Restrictions.like("noticeTime", "%" + noticeTime + "%"));
		}

		List<Notice> noticeList = noticeService.findByCriteria(dc);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("noticeTime", noticeTime);
		model.addAttribute("noticeTitle", noticeTitle);
		model.addAttribute("mainPage", "/dormManage/noticeList.jsp");
		return "dormManage/index";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer noticeId) {
		Notice notice = noticeService.getNotice(noticeId);
		model.addAttribute("notice", notice);
		model.addAttribute("mainPage", "/dormManage/noticeView.jsp");
		return "dormManage/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("notice", new Notice());
		model.addAttribute("mainPage", "/dormManage/noticeView.jsp");
		return "dormManage/index";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes attr, Notice notice, Integer noticeId) {
		String message = "";
		if (noticeId == 0) {
			message = noticeService.save(notice);
		} else {
			message = noticeService.update(notice);
		}
		attr.addFlashAttribute("message", message);
		return "redirect:getNoticeList.action";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(RedirectAttributes attr, Integer noticeId) {
		String message = noticeService.delete(noticeId);
		attr.addFlashAttribute("message", message);
		return "redirect:getNoticeList.action";
	}

}
