package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.NoticeDao;
import com.mcx.model.Notice;
import com.mcx.service.INoticeService;

@Service
public class NoticeService implements INoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public Notice getNotice(Integer noticeId) {
		return noticeDao.get(Notice.class, noticeId);
	}

	@Override
	public List<Notice> findByCriteria(DetachedCriteria detachedCriteria) {
		return noticeDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Notice notice) {
		String message = "";
		try {
			noticeDao.save(notice);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Notice notice) {
		String message = "";
		try {
			noticeDao.update(notice);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer noticeId) {
		Notice notice = getNotice(noticeId);
		String message = "";
		try {
			if (notice != null) {
				noticeDao.delete(notice);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Notice findEntityByCriteria(DetachedCriteria dc) {
		return noticeDao.findEntityByCriteria(dc);
	}

}
