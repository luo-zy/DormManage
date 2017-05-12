package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Notice;

public interface INoticeService {

	public Notice getNotice(Integer noticeId);
	
	public Notice findEntityByCriteria(DetachedCriteria dc);

	public List<Notice> findByCriteria(DetachedCriteria detachedCriteria);

	public String save(Notice notice);

	public String update(Notice notice);

	public String delete(Integer noticeId);
}
